import community.fabricmc.ssg.SSG

fun main() {
    val ssg = SSG {
        defaultTemplate = "test"

        outputPath = "test/output"
        sourcesPath = "test"
        templatePath = "test/templates"
        codePath = "test/code"

        section("docs")
    }

    ssg.render()
}
