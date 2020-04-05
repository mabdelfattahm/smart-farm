package mofa.sf.db

import java.time.Instant

interface DbRow {
    fun getInt(): Int
    fun getLong(): Long
    fun getDouble(): Double
    fun getString(): String
    fun getInstant(): Instant
}
