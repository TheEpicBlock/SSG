package community.fabricmc.ssg.builders

import java.io.File

public class SSGBuilder {
    public lateinit var templatePath: String
    public lateinit var defaultTemplate: String

    public val sections: MutableMap<String, String> = mutableMapOf()

    public fun section(name: String, path: String) {
        if (sections.containsKey(name)) {
            error("Section already registered: $name")
        }

        sections[name] = path
    }

    public fun validate() {
        if (!::templatePath.isInitialized) {
            error("Template path must be specified.")
        }

        if (!::defaultTemplate.isInitialized) {
            error("Default template name must be specified.")
        }

        if (!File(templatePath).exists()) {
            error("No such template path: $templatePath")
        }
    }
}
