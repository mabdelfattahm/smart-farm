package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.domain.reading.Timestamp
import mofa.sf.h2.config.DbConnectionPool
import mofa.sf.h2.repository.SensorReadingRepo
import mofa.sf.rest.dto.AverageHumidityDto
import mofa.sf.usecase.reading.SensorReadingStorage
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.temporal.TemporalAccessor
import java.util.concurrent.CompletableFuture

interface AverageHumidity {
    fun list(): CompletableFuture<Collection<AverageHumidityDto>>

    class Default(private val pool: DbConnectionPool): AverageHumidity {
        override fun list(): CompletableFuture<Collection<AverageHumidityDto>> {
            return CoroutineScope(Dispatchers.IO).future {
                SensorReadingStorage(SensorReadingRepo(pool)).averageHumidity().map { AverageHumidityDto(it) }
            }
        }
    }

    class FilteredByTime(private val pool: DbConnectionPool, private val from: Long, private val to: Long): AverageHumidity {
        init {
            if (Instant.ofEpochSecond(this.to).isBefore(Instant.ofEpochSecond(this.from))) {
                throw IllegalArgumentException("To must be after From")
            }
        }

        override fun list(): CompletableFuture<Collection<AverageHumidityDto>> {
            val ts = Timestamp.Default(this.from)
            val other = Timestamp.Default(this.to)
            return CoroutineScope(Dispatchers.IO).future {
                SensorReadingStorage(SensorReadingRepo(pool)).averageHumidity(ts, other).map { AverageHumidityDto(it) }
            }
        }
    }
}