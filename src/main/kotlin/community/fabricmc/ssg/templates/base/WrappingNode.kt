package community.fabricmc.ssg.templates.base

import com.mitchellbosecke.pebble.extension.NodeVisitor
import com.mitchellbosecke.pebble.node.AbstractRenderableNode
import com.mitchellbosecke.pebble.node.RenderableNode
import com.mitchellbosecke.pebble.template.EvaluationContextImpl
import com.mitchellbosecke.pebble.template.PebbleTemplateImpl
import java.io.StringWriter
import java.io.Writer

public abstract class WrappingNode(
    lineNumber: Int,
    private val children: List<RenderableNode> = listOf(),
    public val className: String?
) : AbstractRenderableNode(lineNumber) {
    public abstract val prefix: String
    public abstract val suffix: String

    override fun accept(visitor: NodeVisitor): Unit = visitor.visit(this)

    override fun render(self: PebbleTemplateImpl, writer: Writer, context: EvaluationContextImpl) {
        val wrappingWriter = StringWriter()

        writer.write("\n$prefix\n\n")

        for (child in this.children) {
            child.render(self, wrappingWriter, context)
        }

        writer.write(wrappingWriter.toString().trimIndent())
        writer.write("\n\n$suffix\n")
    }
}
