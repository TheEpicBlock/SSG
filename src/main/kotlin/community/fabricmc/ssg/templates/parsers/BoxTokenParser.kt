package community.fabricmc.ssg.templates.parsers

import com.mitchellbosecke.pebble.node.AbstractRenderableNode
import community.fabricmc.ssg.templates.base.WrappingTokenParser
import community.fabricmc.ssg.templates.nodes.BoxNode

public class BoxTokenParser : WrappingTokenParser() {
    override val name: String = "box"
    override val builder: (Int, List<AbstractRenderableNode>, String?) -> AbstractRenderableNode = ::BoxNode
}
