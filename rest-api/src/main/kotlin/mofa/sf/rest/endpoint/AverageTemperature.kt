package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.domain.reading.Timestamp
import mofa.sf.h2.config.DbConnectionPool
import mofa.sf.h2.repository.SensorReadingRepo
import mofa.sf.rest.dto.AverageTemperatureDto
import mofa.sf.usecase.reading.SensorReadingStorage
import java.time.Instant
import java.util.concurrent.CompletableFuture

interface AverageTemperature {
    fun list(): CompletableFuture<Collection<AverageTemperatureDto>>

    class Default(private val pool: DbConnectionPool): AverageTemperature {
        override fun list(): CompletableFuture<Collection<AverageTemperatureDto>> {
            return CoroutineScope(Dispatchers.IO).future {
                SensorReadingStorage(SensorReadingRepo(pool)).averageTemperature().map { AverageTemperatureDto(it) }
            }
        }
    }

    class FilteredByTime(private val pool: DbConnectionPool, private val from: Long, private val to: Long): AverageTemperature {
        init {
            if (Instant.ofEpochSecond(this.to).isBefore(Instant.ofEpochSecond(this.from))) {
                throw IllegalArgumentException("To must be after From")
            }
        }

        override fun list(): CompletableFuture<Collection<AverageTemperatureDto>> {
            val ts = Timestamp.Default(this.from)
            val other = Timestamp.Default(this.to)
            return CoroutineScope(Dispatchers.IO).future {
                SensorReadingStorage(SensorReadingRepo(pool)).averageTemperature(ts, other).map { AverageTemperatureDto(it) }
            }
        }
    }
}