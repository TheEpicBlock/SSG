package community.fabricmc.ssg.templates.base

import com.mitchellbosecke.pebble.error.ParserException
import com.mitchellbosecke.pebble.lexer.Token
import com.mitchellbosecke.pebble.node.AbstractRenderableNode
import com.mitchellbosecke.pebble.node.RenderableNode
import com.mitchellbosecke.pebble.parser.Parser
import com.mitchellbosecke.pebble.tokenParser.TokenParser

public abstract class WrappingTokenParser : TokenParser {
    public abstract val name: String
    public abstract val builder: (Int, List<AbstractRenderableNode>, String?) -> AbstractRenderableNode

    private val endName
        get() = if (name.contains("_")) {
            "end_$name"
        } else {
            "end$name"
        }

    override fun getTag(): String = name

    override fun parse(token: Token, parser: Parser): RenderableNode {
        val stream = parser.stream
        val lineNumber = token.lineNumber

        val possibleClass = stream.next()

        val className = if (possibleClass.test(Token.Type.STRING)) {
            stream.next()
            possibleClass.value
        } else {
            null
        }

        stream.expect(Token.Type.EXECUTE_END)

        val body = parser.subparse { tkn: Token ->
            tkn.test(
                Token.Type.NAME,
                endName
            )
        }

        val endToken = stream.current()

        if (!endToken.test(Token.Type.NAME, endName)) {
            throw ParserException(
                null,
                "$endName tag should be present with $name tag starting line number ",
                token.lineNumber,
                stream.filename
            )
        }

        stream.next()
        stream.expect(Token.Type.EXECUTE_END)

        return builder(lineNumber, listOf(body), className)
    }
}
