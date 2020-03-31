package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.domain.reading.Timestamp
import mofa.sf.postgres.config.DbConnectionProvider
import mofa.sf.postgres.repository.SensorReadingRepo
import mofa.sf.rest.dto.AverageHumidityDto
import mofa.sf.usecase.reading.SensorReadingStorage
import java.time.Instant
import java.util.concurrent.CompletableFuture

interface AverageHumidity {
    fun list(): CompletableFuture<Collection<AverageHumidityDto>>

    class Default(private val provider: DbConnectionProvider): AverageHumidity {
        override fun list(): CompletableFuture<Collection<AverageHumidityDto>> {
            return CoroutineScope(Dispatchers.IO).future {
                SensorReadingStorage(SensorReadingRepo(provider)).averageHumidity().map { AverageHumidityDto(it) }
            }
        }
    }

    class FilteredByTime(private val provider: DbConnectionProvider, private val from: Long, private val to: Long): AverageHumidity {
        init {
            if (Instant.ofEpochMilli(this.to).isBefore(Instant.ofEpochMilli(this.from))) {
                throw IllegalArgumentException("To must be after From")
            }
        }

        override fun list(): CompletableFuture<Collection<AverageHumidityDto>> {
            val ts = Timestamp.Default(this.from)
            val other = Timestamp.Default(this.to)
            return CoroutineScope(Dispatchers.IO).future {
                SensorReadingStorage(SensorReadingRepo(provider)).averageHumidity(ts, other).map { AverageHumidityDto(it) }
            }
        }
    }
}