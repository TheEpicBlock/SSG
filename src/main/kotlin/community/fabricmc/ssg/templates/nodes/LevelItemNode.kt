package community.fabricmc.ssg.templates.nodes

import com.mitchellbosecke.pebble.node.RenderableNode
import community.fabricmc.ssg.templates.base.WrappingNode

public class LevelItemNode(
    lineNumber: Int,
    children: List<RenderableNode> = listOf(),
    className: String?
) : WrappingNode(lineNumber, children, className) {
    override val prefix: String = "<div class=\"level-item ${className ?: ""}\"><div>"
    override val suffix: String = "</div></div>"
}
