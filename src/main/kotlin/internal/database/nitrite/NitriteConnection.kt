package effie.soft.internal.database.nitrite

import effie.soft.external.model.GameData
import org.dizitart.kno2.nitrite
import org.dizitart.kno2.serialization.KotlinXSerializationMapper
import org.dizitart.no2.Nitrite
import org.dizitart.no2.common.module.NitriteModule.module
import org.dizitart.no2.mvstore.MVStoreModule
import org.dizitart.no2.repository.ObjectRepository

internal class NitriteConnection(private val dbpath: String) {

    private lateinit var db: Nitrite

    init {
        openDb()
    }

    private fun openDb() {
        val storeModule = MVStoreModule.withConfig()
            .filePath(dbpath)
            .build()

        db = nitrite {
            loadModule(storeModule)
            registerEntityConverter(GameDataConverter())
            enableRepositoryValidation = false
            loadModule(module(KotlinXSerializationMapper()))
        }
    }

    fun getGamesRepository(): ObjectRepository<GameData> {
        return db.getRepository(GameDataDecorator())
    }

}