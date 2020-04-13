package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.db.DbConnectionPool
import mofa.sf.db.h2.repository.SensorReadingRepo
import mofa.sf.domain.reading.Timestamp
import mofa.sf.rest.dto.response.AverageHumidityResponse
import mofa.sf.usecase.reading.SensorReadingStorage
import java.time.Instant
import java.util.concurrent.CompletableFuture

interface GetAverageHumidity {
    fun list(): CompletableFuture<Collection<AverageHumidityResponse>>

    class Default(private val pool: DbConnectionPool): GetAverageHumidity {
        override fun list(): CompletableFuture<Collection<AverageHumidityResponse>> {
            return CoroutineScope(Dispatchers.IO).future {
                SensorReadingStorage(SensorReadingRepo(pool.get()))
                    .averageHumidity().map { AverageHumidityResponse(it) }
            }
        }
    }

    class FilteredByTime(private val pool: DbConnectionPool, private val from: Long, private val to: Long): GetAverageHumidity {
        init {
            if (Instant.ofEpochSecond(this.to).isBefore(Instant.ofEpochSecond(this.from))) {
                throw IllegalArgumentException("To must be after From")
            }
        }

        override fun list(): CompletableFuture<Collection<AverageHumidityResponse>> {
            val ts = Timestamp.Default(this.from)
            val other = Timestamp.Default(this.to)
            return CoroutineScope(Dispatchers.IO).future {
                SensorReadingStorage(SensorReadingRepo(pool.get()))
                    .averageHumidity(ts, other).map { AverageHumidityResponse(it) }
            }
        }
    }
}