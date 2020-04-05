package mofa.sf.db

interface DbResult {
    suspend fun rows(): List<DbRow>
}
