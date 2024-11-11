package effie.soft.internal.database

import effie.soft.external.database.AbstractDatabaseManager
import effie.soft.external.model.GameData

internal class DatabaseManager(private val database: AbstractDatabaseManager) {

    fun addGame(game: GameData) {
        database.addGame(game)
    }

    fun getGameByTitle(search: String): GameData? {
        return database.getGameByTitle(search)
    }

    fun getGameById(id: String): GameData? {
        return database.getGameById(id)
    }

    fun getAllGames(): List<GameData>? {
        return database.getAllGames()
    }

    fun getGamesCount(): Long {
        return database.getGamesCount()
    }

}