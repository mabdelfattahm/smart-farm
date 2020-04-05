package mofa.sf.db

interface DbConnection {
    suspend fun execute(query: String, vararg params: Any): DbResult
}
