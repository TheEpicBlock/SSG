package community.fabricmc.ssg.navigation

public data class Node(
    val file: String,

    val slug: String? = null,
    val children: List<Node> = listOf()
)
