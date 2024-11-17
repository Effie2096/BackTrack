package internal.database.nitrite

import effie.soft.external.model.GameData
import effie.soft.internal.database.nitrite.GameDataConverter
import org.dizitart.kno2.documentOf
import org.dizitart.kno2.serialization.KotlinXSerializationMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class GameDataConverterTest {

    @Test
    fun toDocument() {
        val gameDataConverter = GameDataConverter()

        val gameId = Uuid.random().toString()
        val title = "game title"

        val gameObject = GameData(gameId, title)

        val gameDocument = documentOf(
            "gameId" to gameId,
            "title" to title
        )

        assertEquals(
            gameDocument, gameDataConverter.toDocument(
                gameObject,
                KotlinXSerializationMapper()
            )
        )
    }

    @Test
    fun fromDocument() {
        val gameDataConverter = GameDataConverter()

        val gameId = Uuid.random().toString()
        val title = "game title"

        val gameObject = GameData(gameId, title)

        val gameDocument = documentOf(
            "gameId" to gameId,
            "title" to title
        )

        assertEquals(
            gameObject, gameDataConverter.fromDocument(
                gameDocument,
                KotlinXSerializationMapper()
            )
        )
    }

}