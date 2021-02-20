package community.fabricmc.ssg.templates.parsers

import com.mitchellbosecke.pebble.node.AbstractRenderableNode
import community.fabricmc.ssg.templates.base.WrappingTokenParser
import community.fabricmc.ssg.templates.nodes.LevelRightNode

public class LevelRightTokenParser : WrappingTokenParser() {
    override val name: String = "level_right"
    override val builder: (Int, List<AbstractRenderableNode>, String?) -> AbstractRenderableNode =
        ::LevelRightNode
}
