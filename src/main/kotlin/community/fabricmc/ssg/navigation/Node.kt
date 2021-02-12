package community.fabricmc.ssg.navigation

import kotlinx.serialization.Serializable

@Serializable
public data class Node(
    val path: String,
    val title: String,

    val children: List<Node> = listOf(),
    val description: String? = null
) {
    override fun toString(): String =
        "Node(path = \"$path\", title = \"$title\", children = [${children.joinToString(", ")}])"
}
