package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.db.DbConnectionPool
import mofa.sf.db.h2.repository.SensorReadingRepo
import mofa.sf.domain.reading.Timestamp
import mofa.sf.rest.dto.response.AverageTemperatureResponse
import mofa.sf.usecase.reading.SensorReadingStorage
import java.time.Instant
import java.util.concurrent.CompletableFuture

interface GetAverageTemperature {
    fun list(): CompletableFuture<Collection<AverageTemperatureResponse>>

    class Default(private val pool: DbConnectionPool): GetAverageTemperature {
        override fun list(): CompletableFuture<Collection<AverageTemperatureResponse>> {
            return CoroutineScope(Dispatchers.IO).future {
                SensorReadingStorage(SensorReadingRepo(pool.get()))
                    .averageTemperature().map { AverageTemperatureResponse(it) }
            }
        }
    }

    class FilteredByTime(private val pool: DbConnectionPool, private val from: Long, private val to: Long): GetAverageTemperature {
        init {
            if (Instant.ofEpochSecond(this.to).isBefore(Instant.ofEpochSecond(this.from))) {
                throw IllegalArgumentException("To must be after From")
            }
        }

        override fun list(): CompletableFuture<Collection<AverageTemperatureResponse>> {
            val ts = Timestamp.Default(this.from)
            val other = Timestamp.Default(this.to)
            return CoroutineScope(Dispatchers.IO).future {
                SensorReadingStorage(SensorReadingRepo(pool.get()))
                    .averageTemperature(ts, other).map { AverageTemperatureResponse(it) }
            }
        }
    }
}