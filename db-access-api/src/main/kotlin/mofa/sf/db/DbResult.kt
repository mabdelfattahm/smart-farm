package mofa.sf.db

interface DbResult {
    suspend fun records(): List<DbRecord>
    suspend fun recordsAffected(): Int
}
