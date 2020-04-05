package mofa.sf.db.h2.access

import io.r2dbc.pool.ConnectionPool
import io.r2dbc.pool.ConnectionPoolConfiguration
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactoryOptions
import kotlinx.coroutines.reactive.awaitFirst
import mofa.sf.db.DbConnection
import mofa.sf.db.DbConnectionPool

class H2ConnectionPool: DbConnectionPool {
    private var pool: ConnectionPool
    init {
        val directory = "${System.getProperty("user.home")}/AppData/Local/Smart-farm/smart_farm"
        this.pool = ConnectionPool(
            ConnectionPoolConfiguration.builder(
                ConnectionFactories.get(
                    ConnectionFactoryOptions
                        .builder()
                        .option(ConnectionFactoryOptions.DRIVER, "h2")
                        .option(ConnectionFactoryOptions.PROTOCOL, "file")
                        .option(ConnectionFactoryOptions.USER, "smart_farm")
                        .option(ConnectionFactoryOptions.PASSWORD, "smart_farm")
                        .option(ConnectionFactoryOptions.DATABASE, "${directory};AUTO_SERVER=TRUE")
                        .build()
                    )
            ).build()
        )
    }

    override suspend fun get(): DbConnection {
        return H2Connection(this.pool.create().awaitFirst())
    }
}