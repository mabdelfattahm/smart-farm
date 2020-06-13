package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.db.DbConnectionPool
import mofa.sf.db.h2.repository.SensorReadingRepo
import mofa.sf.domain.reading.Reading
import mofa.sf.domain.reading.Timestamp
import mofa.sf.domain.sensor.SensorId
import mofa.sf.rest.dto.response.ReadingResponse
import mofa.sf.usecase.reading.SensorReadingStorage
import java.time.Instant
import java.util.concurrent.CompletableFuture

interface GetSensorReading {
    fun readings(): CompletableFuture<Collection<Reading>>

    class FilteredBySensor(private val pool: DbConnectionPool, private val id: Int): GetSensorReading {
        init {
            if (this.id < 1) {
                throw IllegalArgumentException("Sensor id must be > 0")
            }
        }

        override fun readings(): CompletableFuture<Collection<Reading>> {
            val sensor = SensorId.Default(this.id)
            return CoroutineScope(Dispatchers.IO).future {
                SensorReadingStorage(SensorReadingRepo(pool.get())).forSensor(sensor).map { ReadingResponse(it) }
            }
        }
    }

    class FilteredByTime(private val pool: DbConnectionPool, private val from: Long, private val to: Long): GetSensorReading {
        init {
            if (Instant.ofEpochSecond(this.to).isBefore(Instant.ofEpochSecond(this.from))) {
                throw IllegalArgumentException("To must be after From")
            }
        }

        override fun readings(): CompletableFuture<Collection<Reading>> {
            val ts  = Timestamp.Default(this.from)
            val other = Timestamp.Default(this.to)
            return CoroutineScope(Dispatchers.IO).future {
                SensorReadingStorage(SensorReadingRepo(pool.get())).list(ts, other).map { ReadingResponse(it) }
            }
        }
    }
}