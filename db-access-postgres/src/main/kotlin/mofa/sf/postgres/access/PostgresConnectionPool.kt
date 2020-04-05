package mofa.sf.postgres.access

import com.github.jasync.sql.db.ConnectionPoolConfigurationBuilder
import com.github.jasync.sql.db.interceptor.LoggingInterceptorSupplier
import com.github.jasync.sql.db.pool.ConnectionPool
import com.github.jasync.sql.db.postgresql.PostgreSQLConnection
import com.github.jasync.sql.db.postgresql.PostgreSQLConnectionBuilder
import mofa.sf.db.DbConnection
import mofa.sf.db.DbConnectionPool
import java.util.concurrent.TimeUnit

class PostgresConnectionPool: DbConnectionPool {
    private val connection: ConnectionPool<PostgreSQLConnection>

    init {
        val config = ConnectionPoolConfigurationBuilder()
        config.username = "postgres"
        config.password = ""
        config.host = "localhost"
        config.port = 5432
        config.database = "smart-farm"
        config.maxActiveConnections = 100
        config.maxIdleTime = TimeUnit.MINUTES.toMillis(15)
        config.maxPendingQueries = 10000
        config.connectionValidationInterval = TimeUnit.SECONDS.toMillis(30)
        config.interceptors = arrayListOf(LoggingInterceptorSupplier())
        this.connection = PostgreSQLConnectionBuilder.createConnectionPool(config)
    }

    override suspend fun get(): DbConnection {
        return PostgresConnection(this.connection)
    }
}