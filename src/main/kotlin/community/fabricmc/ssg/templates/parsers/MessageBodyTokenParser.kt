package community.fabricmc.ssg.templates.parsers

import com.mitchellbosecke.pebble.node.AbstractRenderableNode
import community.fabricmc.ssg.templates.base.WrappingTokenParser
import community.fabricmc.ssg.templates.nodes.MessageBodyNode

public class MessageBodyTokenParser : WrappingTokenParser() {
    override val name: String = "message_body"
    override val builder: (Int, List<AbstractRenderableNode>, String?) -> AbstractRenderableNode =
        ::MessageBodyNode
}
