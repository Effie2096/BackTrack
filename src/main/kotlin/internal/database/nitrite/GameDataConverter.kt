package effie.soft.internal.database.nitrite

import effie.soft.external.model.GameData
import org.dizitart.kno2.documentOf
import org.dizitart.no2.collection.Document
import org.dizitart.no2.common.mapper.EntityConverter
import org.dizitart.no2.common.mapper.NitriteMapper

internal class GameDataConverter : EntityConverter<GameData> {

    override fun toDocument(entity: GameData, nitriteMapper: NitriteMapper): Document {
        return documentOf(
            "gameId" to entity.gameId,
            "title" to entity.title
        )
    }

    override fun fromDocument(document: Document, nitriteMapper: NitriteMapper): GameData {
        val id = document.get("gameId", String::class.java)
        val title = document.get("title", String::class.java)

        return GameData(id, title)
    }

    override fun getEntityType(): Class<GameData> {
        return GameData::class.java
    }

}