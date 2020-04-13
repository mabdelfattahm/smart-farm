package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.db.DbConnectionPool
import mofa.sf.db.h2.repository.SensorReadingRepo
import mofa.sf.domain.reading.Reading
import mofa.sf.domain.sensor.SensorId
import mofa.sf.rest.dto.response.ReadingResponse
import mofa.sf.usecase.reading.SensorReadingStorage
import java.util.concurrent.CompletableFuture

interface GetSensorReading {
    fun list(): CompletableFuture<Collection<Reading>>

    class FilteredBySensor(private val pool: DbConnectionPool, private val id: Int): GetSensorReading {
        init {
            if (this.id < 1) {
                throw IllegalArgumentException("Sensor id must be > 0")
            }
        }

        override fun list(): CompletableFuture<Collection<Reading>> {
            val sensor = SensorId.Default(this.id)
            return CoroutineScope(Dispatchers.IO).future {
                SensorReadingStorage(SensorReadingRepo(pool.get())).forSensor(sensor).map { ReadingResponse(it) }
            }
        }
    }

}