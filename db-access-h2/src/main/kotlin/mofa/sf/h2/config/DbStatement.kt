package mofa.sf.h2.config

import io.r2dbc.spi.Result
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.reactive.asFlow

class DbStatement(private val pool: DbConnectionPool) {

    suspend fun execute(query: String, vararg parameters: Any): Result {
        val connection = this.pool.get()
        val statement = connection.createStatement(query)
        parameters.forEachIndexed { index, parameter -> statement.bind(index, parameter) }
        try {
            return statement.execute().asFlow().first()
        } finally {
            connection.close()
        }
    }
}