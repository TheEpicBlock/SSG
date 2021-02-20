package community.fabricmc.ssg.templates.parsers

import com.mitchellbosecke.pebble.node.AbstractRenderableNode
import community.fabricmc.ssg.templates.base.WrappingTokenParser
import community.fabricmc.ssg.templates.nodes.TagsNode

public class TagsTokenParser : WrappingTokenParser() {
    override val name: String = "tags"
    override val builder: (Int, List<AbstractRenderableNode>, String?) -> AbstractRenderableNode = ::TagsNode
}
