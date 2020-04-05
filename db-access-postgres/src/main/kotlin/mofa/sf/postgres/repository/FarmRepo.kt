package mofa.sf.postgres.repository

import mofa.sf.data.FarmDataSource
import mofa.sf.db.DbConnectionPool
import mofa.sf.domain.farm.Farm
import mofa.sf.domain.farm.FarmId
import mofa.sf.postgres.entity.FarmEntity

class FarmRepo(private val connection: DbConnectionPool) : FarmDataSource {
    override suspend fun list(): Collection<Farm> {
        return this.connection
            .get()
            .execute("SELECT * FROM smart_farm.farms ORDER BY id")
            .records()
            .map { FarmEntity(it) }
    }

    override suspend fun findById(id: FarmId): Farm {
        return this.connection
            .get()
            .execute("SELECT * FROM smart_farm.farms WHERE id = ?", id.asString())
            .records()
            .map { FarmEntity(it) }
            .first()
    }
}