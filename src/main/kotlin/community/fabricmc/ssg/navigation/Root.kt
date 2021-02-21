package community.fabricmc.ssg.navigation

import kotlinx.serialization.Serializable

@Serializable
public data class Root(
    val nodes: List<Node>,
    val currentPath: String = "/",  // Filled out later
    val currentExtension: String = ".md"  // Filled out later
) {
    override fun toString(): String =
        "Root(currentPath = \"$currentPath\", currentExtension = \"$currentExtension\", nodes = [${nodes.joinToString(
            ", "
        )}])"
}
