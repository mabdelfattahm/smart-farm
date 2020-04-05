package mofa.sf.db

interface DbConnection {
    suspend fun execute(query: String, vararg parameters: Any): DbResult
}
