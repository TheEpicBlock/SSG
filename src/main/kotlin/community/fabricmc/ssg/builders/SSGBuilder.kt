package community.fabricmc.ssg.builders

public class SSGBuilder {
    public lateinit var templates: String
    public val sections: MutableMap<String, String> = mutableMapOf()

    public fun section(name: String, path: String) {
        if (sections.containsKey(name)) {
            error("Section already registered: $name")
        }

        sections[name] = path
    }

    public fun validate() {
        if (!::templates.isInitialized) {
            error("Templates directory must be specified.")
        }
    }
}
