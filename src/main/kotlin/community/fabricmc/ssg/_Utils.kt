package community.fabricmc.ssg

import java.io.File
import java.util.concurrent.TimeUnit

// https://stackoverflow.com/a/41495542
@Suppress("PrintStackTrace")
public fun String.runCommand(workingDir: File = File(".")): String {
    val parts = this.split("\\s".toRegex())

    val proc = ProcessBuilder(parts.toList())
        .directory(workingDir)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .start()

    proc.waitFor(2, TimeUnit.SECONDS)

    return proc.inputStream.bufferedReader().readText()
}
