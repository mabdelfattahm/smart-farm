package mofa.sf.h2.repository

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import mofa.sf.data.FarmDataSource
import mofa.sf.domain.farm.Farm
import mofa.sf.domain.farm.FarmId
import mofa.sf.h2.config.DbConnectionPool
import mofa.sf.h2.config.DbStatement
import mofa.sf.h2.entity.FarmEntity

class FarmRepo(private val pool: DbConnectionPool) : FarmDataSource {
    override suspend fun list(): Collection<Farm> {
        return DbStatement(this.pool)
            .execute("SELECT * FROM smart_farm.farms ORDER BY id")
            .map { row, _ ->  FarmEntity(row) }
            .asFlow()
            .toList()
    }

    override suspend fun findById(id: FarmId): Farm {
        return DbStatement(this.pool)
            .execute("SELECT * FROM smart_farm.farms WHERE id = ?", id.asString())
            .map { row, _ ->  FarmEntity(row) }
            .asFlow()
            .first()
    }
}