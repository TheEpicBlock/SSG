package community.fabricmc.ssg.templates.parsers

import com.mitchellbosecke.pebble.node.AbstractRenderableNode
import community.fabricmc.ssg.templates.base.WrappingTokenParser
import community.fabricmc.ssg.templates.nodes.ButtonsNode

public class ButtonsTokenParser : WrappingTokenParser() {
    override val name: String = "buttons"
    override val builder: (Int, List<AbstractRenderableNode>, String?) -> AbstractRenderableNode = ::ButtonsNode
}
