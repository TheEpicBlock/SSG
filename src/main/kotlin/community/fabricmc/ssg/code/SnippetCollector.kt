package community.fabricmc.ssg.code

import java.nio.file.Files
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path

@OptIn(ExperimentalPathApi::class)
public fun collectSnippets(codePath: String): Map<String, Snippet> {
    var codePath = Path(codePath)

    Files.walk(codePath).forEach { file ->
        println(file)
    }

    return HashMap()
}