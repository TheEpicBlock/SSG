package community.fabricmc.ssg

import com.mitchellbosecke.pebble.PebbleEngine
import com.mitchellbosecke.pebble.loader.StringLoader
import com.mitchellbosecke.pebble.template.PebbleTemplate
import community.fabricmc.ssg.builders.SSGBuilder
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.div
import kotlin.io.path.relativeTo

@OptIn(ExperimentalContracts::class, ExperimentalPathApi::class)
public class SSG private constructor(public val settings: SSGBuilder) {
    private val pebble = PebbleEngine.Builder()
        .build()

    private val stringPebble = PebbleEngine.Builder()
        .loader(StringLoader())
        .build()

    private val templatePath = Path(settings.templatePath).relativeTo(Path("."))

    public fun getTemplate(name: String): PebbleTemplate {
        val path = templatePath / "$name.peb.hml"

        return pebble.getTemplate(path.toString())
    }

    public fun getStringTemplate(template: String): PebbleTemplate =
        stringPebble.getTemplate(template)

    public companion object {
        public fun invoke(builder: SSGBuilder.() -> Unit): SSG {
            contract {
                callsInPlace(builder)
            }

            val settings = SSGBuilder()

            builder(settings)

            return SSG(settings)
        }
    }
}
