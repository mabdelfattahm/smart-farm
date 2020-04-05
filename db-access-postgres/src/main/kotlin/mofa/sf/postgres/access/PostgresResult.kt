package mofa.sf.postgres.access

import com.github.jasync.sql.db.QueryResult
import mofa.sf.db.DbResult
import mofa.sf.db.DbRecord

class PostgresResult(private val result: QueryResult) : DbResult {
    override suspend fun records(): List<DbRecord> {
        return result.rows.map { PostgresRecord(it) }
    }

    override suspend fun recordsAffected(): Int {
        return this.result.rowsAffected.toInt()
    }
}