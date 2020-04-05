package mofa.sf.db.h2.access

import io.r2dbc.spi.Result
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import mofa.sf.db.DbResult
import mofa.sf.db.DbRecord

class H2Result(private val result: Result) : DbResult {
    override suspend fun records(): List<DbRecord> {
        return result.map { row, metadata -> H2Record(row, metadata) }.asFlow().toList()
    }

    override suspend fun recordsAffected(): Int {
        return this.result.rowsUpdated.awaitFirst()
    }
}