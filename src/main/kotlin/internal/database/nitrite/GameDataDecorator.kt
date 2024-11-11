package effie.soft.internal.database.nitrite

import effie.soft.external.model.GameData
import org.dizitart.no2.index.IndexType
import org.dizitart.no2.repository.EntityDecorator
import org.dizitart.no2.repository.EntityId
import org.dizitart.no2.repository.EntityIndex

class GameDataDecorator : EntityDecorator<GameData> {

    override fun getEntityType(): Class<GameData> {
        return GameData::class.java
    }

    override fun getIdField(): EntityId {
        return EntityId("gameId")
    }

    override fun getIndexFields(): MutableList<EntityIndex> {
        return mutableListOf(
            EntityIndex(IndexType.UNIQUE, "gameId"),
            EntityIndex(IndexType.NON_UNIQUE, "title")
        )
    }

    override fun getEntityName(): String {
        return "games"
    }
}