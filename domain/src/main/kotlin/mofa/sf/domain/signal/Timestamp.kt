package mofa.sf.domain.signal

import java.time.Duration
import java.time.Instant

interface Timestamp {
    fun asLong(): Long
    fun asInstant(): Instant

    class Default(private val value: Long) : Timestamp {
        init {
            if (Duration.ofMillis(value).isNegative) {
                throw IllegalArgumentException("Invalid value")
            }
        }

        override fun asLong(): Long {
            return this.value
        }

        override fun asInstant(): Instant {
            return Instant.ofEpochMilli(this.value)
        }
    }
}