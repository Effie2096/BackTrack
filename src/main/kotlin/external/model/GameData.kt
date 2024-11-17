package effie.soft.external.model

import effie.soft.external.exception.BackTrackErrorCodes
import effie.soft.external.exception.BackTrackException
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
@OptIn(ExperimentalUuidApi::class)
data class GameData(
    val gameId: String = Uuid.random().toString(),
    var title: String
) {


    init {
        try {
            Uuid.parse(gameId)
        } catch (e: IllegalArgumentException) {
            throw BackTrackException("${::gameId.name} is not a valid UUID", e, BackTrackErrorCodes.INVALID_GAMEDATA)
        }
    }
}
