package effie.soft.internal.logging

import io.klogging.Level
import io.klogging.NoCoLogging
import java.lang.Exception

object BackTrackLoggah : NoCoLogging {
    fun log(level: Level, message: String) {
        logger.log(level, message)
    }

    fun log(level: Level, message: String, exception: Exception) {
        logger.log(level, message, exception.localizedMessage)
    }
}