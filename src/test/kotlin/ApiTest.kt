import effie.soft.external.BackTrackAPI
import effie.soft.external.model.GameData
import effie.soft.external.exception.BackTrackException
import org.dizitart.no2.exceptions.UniqueConstraintException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.io.path.Path
import kotlin.io.path.deleteIfExists
import kotlin.test.assertFailsWith
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ApiTest {

    private val backTrack = BackTrackAPI()
    private val testDBName = "testDB.db"

    @BeforeEach
    fun `initialise database`() {
        Path(testDBName).deleteIfExists()
        backTrack.initDatabase(testDBName)
    }

    @Test
    fun whenAddingGame_gameCanBeFoundInGamesTable() {
        val game = GameData(title = "test game lol")

        backTrack.addGame(game)

        val foundGameTitle = backTrack.getGameByTitle(game.title)

        assertEquals(game.gameId, foundGameTitle!!.gameId)
    }

    @Test
    fun whenSearchingGameId_returnedGameInsertedGame() {
        val game = GameData(title = "test game lol")

        backTrack.addGame(game)

        val foundGame = backTrack.getGameById(game.gameId)

        assertEquals(game, foundGame)
        assertEquals(game.gameId, foundGame!!.gameId)
    }

    @Test
    fun whenSearchingGameTitleThatDoesNotExist_returnNull() {
        val gameTitle = "test game lol"

        assertEquals(null, backTrack.getGameByTitle(gameTitle))
    }

    @Test
    fun whenSearchingGameIdThatDoesNotExist_returnNull() {
        val id = Uuid.random().toString()
        val game = GameData(title = "test game lol")

        backTrack.addGame(game)

        assertEquals(null, backTrack.getGameById(id))
    }

    @Test
    fun whenAddingGameWithNonUniqueId_throwsException() {
        val id = Uuid.random().toString()
        val game1 = GameData(id, "game1")
        val game2 = GameData(id, "game2")

        backTrack.addGame(game1)
        val exception = assertFailsWith<BackTrackException> {
            backTrack.addGame(game2)
        }

        assertEquals(exception.cause!!::class, UniqueConstraintException::class)
    }

    @Test
    fun whenCreatingGameWithInvalidId_throwException() {
        val badId = "somebadidlol"
        var game: GameData? = null
        val exception = assertFailsWith<BackTrackException> {
            game = GameData(badId, "test game lol")
        }
        game?.let { backTrack.addGame(it) }

        assertEquals(exception.message, "gameId is not a valid UUID")
        assertEquals(exception.cause!!::class, IllegalArgumentException::class)
    }

    @Test
    fun whenRetrievingAllGames_returnedListIsSameLengthAsGamesTable() {
        val games = listOf(
            GameData(title = "game1"),
            GameData(title = "game2")
        )

        for (game in games) {
            backTrack.addGame(game)
        }

        val allGames = backTrack.getAllGames()

        assertEquals(backTrack.getGamesCount(), allGames!!.size.toLong())
    }

    @Test
    fun whenGamesTableIsEmpty_retrievingAllGamesReturnsNull() {
        assertEquals(null, backTrack.getAllGames())
    }

}