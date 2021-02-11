package community.fabricmc.ssg.builders

import com.github.slugify.Slugify
import java.io.File
import kotlin.io.path.*

@OptIn(ExperimentalPathApi::class)
public class SSGBuilder {
    public lateinit var defaultTemplate: String

    public lateinit var outputPath: String
    public lateinit var sourcesPath: String
    public lateinit var templatePath: String

    private val slugify = Slugify()

    public val sections: MutableList<String> = mutableListOf()

    public fun section(name: String) {
        if (sections.contains(name)) {
            error("Section already registered: $name")
        }

        sections.add(slugify.slugify(name))
    }

    public fun validate() {
        if (!::templatePath.isInitialized) {
            error("Template path must be specified.")
        }

        if (!::sourcesPath.isInitialized) {
            error("Sources path must be specified.")
        }

        if (!::outputPath.isInitialized) {
            error("Output path must be specified.")
        }

        if (!::defaultTemplate.isInitialized) {
            error("Default template name must be specified.")
        }

        if (!File(templatePath).exists()) {
            error("No such template path: $templatePath")
        }

        sections.forEach { section ->
            val path = Path(sourcesPath) / section

            if (!path.exists() || !path.isDirectory()) {
                error("Unable to find section: $section")
            }
        }
    }
}
