package community.fabricmc.ssg

import com.charleskorn.kaml.PolymorphismStyle
import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration
import com.mitchellbosecke.pebble.PebbleEngine
import com.mitchellbosecke.pebble.loader.FileLoader
import com.mitchellbosecke.pebble.template.PebbleTemplate
import community.fabricmc.ssg.builders.SSGBuilder
import community.fabricmc.ssg.markdown.MarkdownRenderer
import community.fabricmc.ssg.navigation.Root
import community.fabricmc.ssg.templates.SSGExtension
import java.io.File
import java.io.StringWriter
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.*
import kotlin.streams.toList

private val ALLOWED_EXTENSIONS = arrayOf("html", "md", "peb")

@OptIn(ExperimentalPathApi::class)
public class SSG private constructor(public val settings: SSGBuilder) {
    private val pebbleLoader = FileLoader()

    init {
        pebbleLoader.prefix = Path(settings.templatePath).absolutePathString() + File.separator
        pebbleLoader.suffix = ".html.peb"

        println("Template loader prefix:    ${pebbleLoader.prefix}")
        println("Template loader suffix:    ${pebbleLoader.suffix}")
        println("")
    }

    private val pebble = PebbleEngine.Builder()
        .cacheActive(false)
        .extension(SSGExtension())
        .loader(pebbleLoader)
        .build()

    public val yaml: Yaml = Yaml(configuration = YamlConfiguration(polymorphismStyle = PolymorphismStyle.Property))
    private val markdown = MarkdownRenderer(this)

    private val templatePath = Path(settings.templatePath).relativeTo(Path("."))

    public fun getTemplate(name: String, log: Boolean = true): PebbleTemplate {
        if (log) {
            println("    Rendering template: $name.html.peb")
        }

        return pebble.getTemplate(name)
    }

    public fun getStringTemplate(template: String): PebbleTemplate {
        val tempFile = templatePath / "temp.html.peb"

        println("    Writing temporary template at $tempFile: ${template.length} chars")

//        println("\n===TEMPLATE CONTENTS===\n$template\n===END TEMPLATE===\n".prependIndent("    "))

        tempFile.writeText(template, Charsets.UTF_8)

        val templateObj = getTemplate("temp", false)

        tempFile.deleteExisting()

        return templateObj
    }

    public fun getSources(section: String? = null): List<Path> {
        var sourcesRoot = Path(settings.sourcesPath)

        val walk = if (section != null) {
            sourcesRoot = sourcesRoot / section

            Files.walk(sourcesRoot)
        } else {
            Files.walk(sourcesRoot, 1)
        }

        return walk.filter { it.extension in ALLOWED_EXTENSIONS }.toList()
    }

    public fun getNavigation(section: String? = null): Root {
        var sourcesRoot = Path(settings.sourcesPath)

        if (section != null) {
            sourcesRoot = sourcesRoot / section
        }

        val navigationFile = sourcesRoot / "navigation.yml"

        println("    Parsing navigation: $navigationFile")

        @Suppress("TooGenericExceptionCaught")
        if (navigationFile.exists()) {
            try {
                return yaml.decodeFromString(Root.serializer(), navigationFile.readText(Charsets.UTF_8))
            } catch (e: Exception) {
                val sectionText = if (section != null) {
                    "/$section/"
                } else {
                    "/"
                }

                error("Failed to parse ${sectionText}navigation.yml: ${e.message ?: e}")
            }
        }

        return Root(listOf())
    }

    @Suppress("StringLiteralDuplication")
    public fun render(section: String?) {
        var outputRoot = Path(settings.outputPath)
        var sourcesRoot = Path(settings.sourcesPath)

        val sources = getSources(section)

        println(
            "Found ${sources.size} source files " +
                    if (section != null) {
                        "for section: $section."
                    } else {
                        "for site root."
                    }
        )

        if (section != null) {
            outputRoot = outputRoot / section
            sourcesRoot = sourcesRoot / section
        }

        if (!outputRoot.exists()) {
            outputRoot.createDirectories()
        }

        println("    Output root: $outputRoot")

        sources.forEach { source ->
            var relativePath = source.relativeTo(sourcesRoot).toString()

            if (relativePath.endsWith(".peb")) {
                relativePath = relativePath.substringBeforeLast(".").substringBeforeLast(".")
            } else {
                relativePath = relativePath.substringBeforeLast(".")
            }

            var outputPath = if (!relativePath.endsWith("index")) {
                (outputRoot / relativePath).createDirectories()

                outputRoot / "$relativePath/index.html"
            } else {
                outputRoot / "$relativePath.html"
            }

            val path = if (relativePath.endsWith("index")) {
                relativePath.substringBeforeLast("index").trim('/')
            } else {
                relativePath.trim('/')
            }

            val slug = "/" + ((section ?: "") + "/$path").trim('/').replace('\\', '/')

            val navigation = getNavigation(section).copy(
                currentPath = slug.trimEnd('/'),
                currentExtension = source.toString().split(".", limit = 2).last()
            )

            println("    Slug: $slug")

            val rendered = if (source.toString().endsWith(".html.peb")) {
                outputPath = if (!relativePath.endsWith("index")) {
                    outputRoot / "$relativePath/index.html"
                } else {
                    outputRoot / "$relativePath.html"
                }

                val template = getStringTemplate(source.readText(Charsets.UTF_8))
                val context: MutableMap<String, Any?> = mutableMapOf("body" to null)

                context["navigation"] = navigation

                val writer = StringWriter()

                template.evaluate(writer, context)

                writer.toString()
            } else if (source.extension == "md" || source.toString().endsWith(".md.peb")) {
                markdown.render(source, navigation)
            } else {
                source.readText(Charsets.UTF_8)
            }

            outputPath.normalize().parent.createDirectories()

            println("    Writing file: $outputPath")

            outputPath.writeText(rendered, Charsets.UTF_8)
            println("\n")
        }
    }

    public fun render() {
        val outputRoot = Path(settings.outputPath)

        if (outputRoot.exists()) {
            outputRoot.toFile().deleteRecursively()
        }

        outputRoot.createDirectories()

        render(null)

        settings.sections.forEach { render(it) }
    }

    public companion object {
        public operator fun invoke(builder: SSGBuilder.() -> Unit): SSG {
            val settings = SSGBuilder()

            builder(settings)
            settings.validate()

            return SSG(settings)
        }
    }
}
