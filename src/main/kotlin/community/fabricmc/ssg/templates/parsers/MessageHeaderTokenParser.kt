package community.fabricmc.ssg.templates.parsers

import com.mitchellbosecke.pebble.node.AbstractRenderableNode
import community.fabricmc.ssg.templates.base.WrappingTokenParser
import community.fabricmc.ssg.templates.nodes.MessageHeaderNode

public class MessageHeaderTokenParser : WrappingTokenParser() {
    override val name: String = "message_header"
    override val builder: (Int, List<AbstractRenderableNode>, String?) -> AbstractRenderableNode =
        ::MessageHeaderNode
}
