package mofa.sf.db

interface DbConnectionPool {
    suspend fun get(): DbConnection
}