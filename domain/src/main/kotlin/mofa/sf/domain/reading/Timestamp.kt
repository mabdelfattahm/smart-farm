package mofa.sf.domain.reading

import java.time.Duration

interface Timestamp {
    fun asLong(): Long

    class Default(private val value: Long) : Timestamp {
        init {
            if (Duration.ofMillis(value).isNegative) {
                throw IllegalArgumentException("Invalid value")
            }
        }

        override fun asLong(): Long {
            return this.value
        }
    }
}