package mofa.sf.postgres.access

import com.github.jasync.sql.db.pool.ConnectionPool
import com.github.jasync.sql.db.postgresql.PostgreSQLConnection
import kotlinx.coroutines.future.await
import mofa.sf.db.DbConnection
import mofa.sf.db.DbResult

class PostgresConnection(private val connection: ConnectionPool<PostgreSQLConnection>) : DbConnection {
    override suspend fun execute(query: String, vararg parameters: Any): DbResult {
        val result = connection.sendPreparedStatement(query, arrayListOf(parameters)).await()
        return PostgresResult(result)
    }
}