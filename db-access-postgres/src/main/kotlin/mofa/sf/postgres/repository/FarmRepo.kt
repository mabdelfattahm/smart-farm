package mofa.sf.postgres.repository

import kotlinx.coroutines.future.await
import mofa.sf.postgres.config.DbConnectionPool
import mofa.sf.data.FarmDataSource
import mofa.sf.domain.farm.Farm
import mofa.sf.domain.farm.FarmId
import mofa.sf.postgres.entity.FarmEntity

class FarmRepo(private val connection: DbConnectionPool) : FarmDataSource {
    override suspend fun list(): Collection<Farm> {
        return this.connection
            .get()
            .sendQuery("SELECT * FROM smart_farm.farms ORDER BY id")
            .await()
            .rows
            .map { FarmEntity(it) }
    }

    override suspend fun findById(id: FarmId): Farm {
        return this.connection
            .get()
            .sendPreparedStatement(
                "SELECT * FROM smart_farm.farms WHERE id = ?",
                arrayListOf(id.asString())
            )
            .await()
            .rows
            .map { FarmEntity(it) }
            .first()
    }
}