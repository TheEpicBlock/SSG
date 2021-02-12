package community.fabricmc.ssg.navigation

import kotlinx.serialization.Serializable

@Serializable
public data class Root(
    val nodes: List<Node>,
    val currentPath: String = "/"  // Filled out later
) {
    override fun toString(): String =
        "Root(currentPath = \"$currentPath\", nodes = [${nodes.joinToString(", ")}])"
}
