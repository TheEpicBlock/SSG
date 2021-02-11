package community.fabricmc.ssg

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.context
import com.github.ajalt.clikt.output.CliktHelpFormatter
import com.github.ajalt.clikt.parameters.options.multiple
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.file
import java.io.File
import kotlin.system.exitProcess

public class Main : CliktCommand(
    help = """
    Generates a set of HTML files represented by the files contained in the given sources path.
    
    Please note that all arguments are required. You may also specify more than one section.
    """
) {
    init {
        context {
            helpFormatter = CliktHelpFormatter(requiredOptionMarker = "*")
        }
    }
    public val defaultTemplate: String by option(help = "Default template name")
        .required()

    public val outputPath: File by option(help = "Generated output path")
        .file(mustExist = false, canBeFile = false)
        .required()

    public val sourcesPath: File by option(help = "Sources path")
        .file(mustExist = true, canBeFile = false, mustBeReadable = true)
        .required()

    public val templatePath: File by option(help = "Templates path")
        .file(mustExist = true, canBeFile = false, mustBeReadable = true)
        .required()

    public val sections: List<String> by option("--section", help = "Section names")
        .multiple(required = true)

    override fun run() {
        println("== FabriComm Static Site Generator ==")
        println("Default template: $defaultTemplate")
        println("Sections:         ${sections.joinToString(", ")}")
        println("")
        println("Output path:      ${outputPath.path}")
        println("Sources path:     ${sourcesPath.path}")
        println("Template path:    ${templatePath.path}")
        println("")

        try {
            val ssg = SSG {
                defaultTemplate = this@Main.defaultTemplate
                outputPath = this@Main.outputPath.path
                sourcesPath = this@Main.sourcesPath.path
                templatePath = this@Main.templatePath.path

                this@Main.sections.forEach { section(it) }
            }

            println("Rendering...")

            ssg.render()

            println("")
            println("Done!")
        } catch (e: IllegalStateException) {
            System.err.println(e.message ?: e.toString())
            exitProcess(1)
        }
    }
}

public fun main(args: Array<String>): Unit = Main().main(args)
