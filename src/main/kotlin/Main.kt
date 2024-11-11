package effie.soft

import io.klogging.config.ANSI_CONSOLE
import io.klogging.config.loggingConfiguration
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    loggingConfiguration { ANSI_CONSOLE }
}