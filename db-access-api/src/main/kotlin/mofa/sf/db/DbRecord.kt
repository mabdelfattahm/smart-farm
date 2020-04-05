package mofa.sf.db

import java.time.LocalDateTime

interface DbRecord {
    fun getInt(column: String): Int
    fun getDouble(column: String): Double
    fun getString(column: String): String
    fun getLocalDate(column: String): LocalDateTime
}
