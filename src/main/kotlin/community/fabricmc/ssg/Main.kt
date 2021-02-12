package community.fabricmc.ssg

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import community.fabricmc.ssg.commands.BuildCommand
import community.fabricmc.ssg.commands.ServeCommand

public class Main : CliktCommand() {
    @Suppress("EmptyFunctionBlock")  // No choice here
    override fun run() {}
}

public fun main(args: Array<String>): Unit = Main().subcommands(BuildCommand(), ServeCommand()).main(args)
