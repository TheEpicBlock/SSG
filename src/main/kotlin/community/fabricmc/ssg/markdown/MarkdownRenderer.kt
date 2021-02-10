package community.fabricmc.ssg.markdown

import com.vladsch.flexmark.ext.abbreviation.AbbreviationExtension
import com.vladsch.flexmark.ext.anchorlink.AnchorLinkExtension
import com.vladsch.flexmark.ext.attributes.AttributesExtension
import com.vladsch.flexmark.ext.autolink.AutolinkExtension
import com.vladsch.flexmark.ext.emoji.EmojiExtension
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension
import com.vladsch.flexmark.ext.media.tags.MediaTagsExtension
import com.vladsch.flexmark.ext.tables.TablesExtension
import com.vladsch.flexmark.ext.toc.TocExtension
import com.vladsch.flexmark.ext.wikilink.WikiLinkExtension
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterExtension
import com.vladsch.flexmark.ext.youtube.embedded.YouTubeLinkExtension
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.DataSet
import com.vladsch.flexmark.util.data.MutableDataSet
import com.vladsch.flexmark.util.misc.Extension

public class MarkdownRenderer(sectionRoot: String) {
    private var settings: DataSet = MutableDataSet()
        .set(HtmlRenderer.GENERATE_HEADER_ID, true)

        .toImmutable()

    private var extensions: MutableList<Extension> = mutableListOf(
        AbbreviationExtension.create(),
        AnchorLinkExtension.create(),
        AttributesExtension.create(),
        AutolinkExtension.create(),
        EmojiExtension.create(),
        StrikethroughExtension.create(),
        MediaTagsExtension.create(),
        TablesExtension.create(),
        TocExtension.create(),
        WikiLinkExtension.create(),
        YamlFrontMatterExtension.create(),
        YouTubeLinkExtension.create()
    )

    public val parser: Parser = Parser.builder(settings).extensions(extensions).build()
    public val renderer: HtmlRenderer = HtmlRenderer.builder(settings).extensions(extensions).build()

    public fun render() {

    }
}
