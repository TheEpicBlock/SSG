package community.fabricmc.ssg.templates.parsers

import com.mitchellbosecke.pebble.node.AbstractRenderableNode
import community.fabricmc.ssg.templates.base.WrappingTokenParser
import community.fabricmc.ssg.templates.nodes.LevelLeftNode

public class LevelLeftTokenParser : WrappingTokenParser() {
    override val name: String = "level_left"
    override val builder: (Int, List<AbstractRenderableNode>, String?) -> AbstractRenderableNode =
        ::LevelLeftNode
}
