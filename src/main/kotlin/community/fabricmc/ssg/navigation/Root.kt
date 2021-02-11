package community.fabricmc.ssg.navigation

import kotlinx.serialization.Serializable

@Serializable
public data class Root(
    val children: List<Node>
)
