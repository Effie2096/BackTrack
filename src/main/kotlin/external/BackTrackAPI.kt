package effie.soft.external

import effie.soft.external.model.GameData
import effie.soft.internal.database.DatabaseManager
import effie.soft.internal.database.nitrite.NitriteManager
import effie.soft.external.exception.BackTrackException


class BackTrackAPI {

    private lateinit var db: DatabaseManager

    /**
     * Adds the given GameData object to the database.
     *
     * @param game [GameData] GameData object representing game to be inserted.
     * @throws [BackTrackException] Wrapped exception with extra context information.
     */
    fun addGame(game: GameData) {
        db.addGame(game)
    }

    /**
     * Retrieve GameData object that matches [title] if any are found in database.
     *
     * @param title [String] Full title of game to search for.
     * @return [GameData?] GameData object matching search or null if none found.
     * @throws [BackTrackException] wrapped exception with extra context information.
     */
    fun getGameByTitle(title: String): GameData? {
        return db.getGameByTitle(title)
    }

    /**
     * Retrieve GameData object that matches [id] if any are found in database.
     *
     * @param id [String] Uuid compliant string to search for
     * @return [GameData?] GameData object matching search or null if none found.
     * @throws [BackTrackException] wrapped exception with extra context information.
     */
    fun getGameById(id: String): GameData? {
        return db.getGameById(id)
    }

    /**
     * Retrieve [List] of GameData objects of all games in database.
     *
     * @return [List<GameData>?] List of GameData objects or null if table empty.
     * @throws [BackTrackException] wrapped exception with extra context information.
     */
    fun getAllGames(): List<GameData>? {
        return db.getAllGames()
    }

    /**
     * Get number of games in games table.
     *
     * @return [Long] Number of games in games table
     * @throws [BackTrackException] wrapped exception with extra context information.
     */
    fun getGamesCount(): Long {
        return db.getGamesCount()
    }

    /**
     * Reinitialize database connection with given path
     *
     * @param dbPath [String] String representing path to database connection.
     * @throws [BackTrackException] wrapped exception with extra context information.
     */
    fun initDatabase(dbPath: String) {
        db = DatabaseManager(NitriteManager(dbPath))
    }

}