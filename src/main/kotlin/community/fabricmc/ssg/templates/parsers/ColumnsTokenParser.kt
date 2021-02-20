package community.fabricmc.ssg.templates.parsers

import com.mitchellbosecke.pebble.node.AbstractRenderableNode
import community.fabricmc.ssg.templates.base.WrappingTokenParser
import community.fabricmc.ssg.templates.nodes.ColumnsNode

public class ColumnsTokenParser : WrappingTokenParser() {
    override val name: String = "columns"
    override val builder: (Int, List<AbstractRenderableNode>, String?) -> AbstractRenderableNode =
        ::ColumnsNode
}
