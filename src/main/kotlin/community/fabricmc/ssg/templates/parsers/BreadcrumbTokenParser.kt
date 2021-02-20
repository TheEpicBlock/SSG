package community.fabricmc.ssg.templates.parsers

import com.mitchellbosecke.pebble.node.AbstractRenderableNode
import community.fabricmc.ssg.templates.base.WrappingTokenParser
import community.fabricmc.ssg.templates.nodes.BreadcrumbNode

public class BreadcrumbTokenParser : WrappingTokenParser() {
    override val name: String = "breadcrumb"
    override val builder: (Int, List<AbstractRenderableNode>, String?) -> AbstractRenderableNode = ::BreadcrumbNode
}
