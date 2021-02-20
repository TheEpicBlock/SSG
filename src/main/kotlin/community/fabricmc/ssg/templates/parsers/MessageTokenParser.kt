package community.fabricmc.ssg.templates.parsers

import com.mitchellbosecke.pebble.node.AbstractRenderableNode
import community.fabricmc.ssg.templates.base.WrappingTokenParser
import community.fabricmc.ssg.templates.nodes.MessageNode

public class MessageTokenParser : WrappingTokenParser() {
    public override val name: String = "message"
    public override val builder: (Int, List<AbstractRenderableNode>, String?) -> AbstractRenderableNode = ::MessageNode
}
