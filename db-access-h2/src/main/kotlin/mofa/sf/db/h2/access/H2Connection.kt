package mofa.sf.db.h2.access

import io.r2dbc.spi.Connection
import kotlinx.coroutines.reactive.awaitFirst
import mofa.sf.db.DbConnection
import mofa.sf.db.DbResult

class H2Connection(private val connection: Connection) : DbConnection {
    override suspend fun execute(query: String, vararg parameters: Any): DbResult {
        val statement = connection.createStatement(query).returnGeneratedValues()
        parameters.forEachIndexed { index, parameter -> statement.bind(index, parameter) }
        try {
            return H2Result(statement.execute().awaitFirst())
        } finally {
            connection.close()
        }
    }
}