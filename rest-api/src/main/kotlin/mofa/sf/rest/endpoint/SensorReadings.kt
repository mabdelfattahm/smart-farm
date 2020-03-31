package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.postgres.config.DbConnectionProvider
import mofa.sf.domain.reading.Reading
import mofa.sf.domain.reading.Timestamp
import mofa.sf.domain.sensor.SensorId
import mofa.sf.postgres.repository.SensorReadingRepo
import mofa.sf.rest.dto.ReadingDto
import mofa.sf.usecase.reading.SensorReadingStorage
import java.time.Instant
import java.util.concurrent.CompletableFuture

interface SensorReadings {
    fun list(): CompletableFuture<Collection<Reading>>

    class FilteredBySensor(private val provider: DbConnectionProvider, private val id: Int): SensorReadings {
        init {
            if (this.id < 1) {
                throw IllegalArgumentException("Sensor id must be > 0")
            }
        }

        override fun list(): CompletableFuture<Collection<Reading>> {
            val sensor = SensorId.Default(this.id)
            return CoroutineScope(Dispatchers.IO).future {
                SensorReadingStorage(SensorReadingRepo(provider)).forSensor(sensor).map { ReadingDto(it) }
            }
        }
    }

}