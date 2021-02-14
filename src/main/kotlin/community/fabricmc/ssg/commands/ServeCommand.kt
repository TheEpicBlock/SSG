package community.fabricmc.ssg.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.file
import com.github.ajalt.clikt.parameters.types.int
import io.javalin.Javalin
import io.javalin.http.staticfiles.Location
import java.io.File

private const val DEFAULT_PORT = 8080

public class ServeCommand : CliktCommand(
    help = """
    Starts a simple web server that serves the website from the given output path. This does not build the site
    automatically - you'll still need to use the `build` command for that.
    """
) {
    public val outputPath: File by option(help = "Generated output path")
        .file(mustExist = false, canBeFile = false)
        .required()

    public val port: Int by option(help = "Port to listen on")
        .int()
        .default(DEFAULT_PORT)

    override fun run() {
        val app = Javalin.create { config ->
            config.addStaticFiles(outputPath.path, Location.EXTERNAL)
        }.start("localhost", port)

        Runtime.getRuntime().addShutdownHook(Thread() {
            app.stop()
        })
    }
}
