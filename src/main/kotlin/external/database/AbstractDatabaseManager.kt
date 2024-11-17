package effie.soft.external.database

import effie.soft.external.model.GameData

abstract class AbstractDatabaseManager() {

    abstract fun addGame(gameData: GameData)
    abstract fun getGameByTitle(search: String): GameData?
    abstract fun getGameById(id: String): GameData?
    abstract fun getAllGames(): List<GameData>?
    abstract fun getGamesCount(): Long
}