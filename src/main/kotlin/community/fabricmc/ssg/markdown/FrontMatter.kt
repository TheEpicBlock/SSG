package community.fabricmc.ssg.markdown

import kotlinx.serialization.Serializable

@Serializable
public data class FrontMatter(
    public val title: String,

    public val template: String? = null,
    public val slug: String? = null,
    public val authors: List<String> = listOf()
)
