package community.fabricmc.ssg

import community.fabricmc.ssg.builders.SSGBuilder
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
public class SSG private constructor(private val settings: SSGBuilder) {

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
