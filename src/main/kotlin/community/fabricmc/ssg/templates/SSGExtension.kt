package community.fabricmc.ssg.templates

import com.mitchellbosecke.pebble.extension.AbstractExtension
import com.mitchellbosecke.pebble.tokenParser.TokenParser
import community.fabricmc.ssg.templates.parsers.*

public class SSGExtension : AbstractExtension() {
    private val tokenParsers = mutableListOf<TokenParser>(
        BoxTokenParser(),
        BreadcrumbTokenParser(),
        ButtonsTokenParser(),
        ColumnTokenParser(),
        ColumnsTokenParser(),
        ImageTokenParser(),
        LevelItemTokenParser(),
        LevelLeftTokenParser(),
        LevelRightTokenParser(),
        LevelTokenParser(),
        MessageBodyTokenParser(),
        MessageHeaderTokenParser(),
        MessageTokenParser(),
        TagsTokenParser(),
    )

    override fun getTokenParsers(): MutableList<TokenParser> = tokenParsers
}
