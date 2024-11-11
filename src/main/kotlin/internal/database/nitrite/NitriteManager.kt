package effie.soft.internal.database.nitrite

import effie.soft.external.database.AbstractDatabaseManager
import effie.soft.external.model.GameData
import effie.soft.internal.logging.BackTrackLoggah.log as log
import effie.soft.external.exception.BackTrackErrorCodes
import effie.soft.external.exception.BackTrackException
import io.klogging.Level
import org.dizitart.kno2.filters.eq
import org.dizitart.no2.exceptions.InvalidIdException
import org.dizitart.no2.exceptions.UniqueConstraintException
import org.dizitart.no2.exceptions.ValidationException
import org.dizitart.no2.filters.Filter

internal class NitriteManager(databasePath: String) : AbstractDatabaseManager() {

    private var db = NitriteConnection(databasePath)

    override fun addGame(gameData: GameData) {
        try {
            db.getGamesRepository().insert(gameData)
        } catch (e: ValidationException) {
            e.message?.let { log(Level.INFO, it, e) }
        } catch (e: InvalidIdException) {
            val reason = "Malformed gameId"
            log(Level.ERROR, reason, e)
            throw BackTrackException(reason, e, BackTrackErrorCodes.DATABASE_ERROR)
        } catch (e: UniqueConstraintException) {
            val reason = "Duplicate value entered for unique field. Skipping insertion."
            log(Level.WARN, reason, e)
            throw BackTrackException(reason, e, BackTrackErrorCodes.DATABASE_ERROR)
        }
    }

    private fun searchRepository(filter: Filter): List<GameData>? {
        val searchResult = db.getGamesRepository()
            .find(filter)

        if (searchResult.size() == 0.toLong()) {
            return null
        }

        return searchResult.toList()
    }

    override fun getGameByTitle(search: String): GameData? {
        val searchResult = searchRepository(GameData::title eq search)

        return searchResult?.firstOrNull()
    }

    override fun getGameById(id: String): GameData? {
        val searchResult = searchRepository(GameData::gameId eq id)

        return searchResult?.firstOrNull()
    }

    override fun getAllGames(): List<GameData>? {
        val allGames = db.getGamesRepository().find().toList()

        return if (allGames.isNullOrEmpty()) { null } else { allGames }
    }

    override fun getGamesCount(): Long {
        return db.getGamesRepository().size()
    }

    internal fun setDatabase(db: NitriteConnection) {
        this.db = db
    }
}