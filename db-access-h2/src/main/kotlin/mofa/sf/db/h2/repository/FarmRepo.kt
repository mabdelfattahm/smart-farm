package mofa.sf.db.h2.repository

import mofa.sf.data.FarmDataSource
import mofa.sf.db.DbConnection
import mofa.sf.db.h2.entity.FarmEntity
import mofa.sf.domain.farm.Farm
import mofa.sf.domain.farm.FarmId

class FarmRepo(private val connection: DbConnection) : FarmDataSource {
    override suspend fun list(): Collection<Farm> {
        return this.connection
            .execute("SELECT * FROM smart_farm.farms ORDER BY id")
            .records()
            .map { FarmEntity(it) }
    }

    override suspend fun findById(id: FarmId): Farm {
        return this.connection
            .execute("SELECT * FROM smart_farm.farms WHERE id = ?", id.asString())
            .records()
            .map { FarmEntity(it) }
            .first()
    }
}