package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.db.DbConnectionPool
import mofa.sf.db.h2.repository.SensorReadingRepo
import mofa.sf.domain.reading.Reading
import mofa.sf.domain.reading.ReadingId
import mofa.sf.rest.dto.response.ReadingIdResponse
import mofa.sf.usecase.reading.SensorReadingStorage
import java.util.concurrent.CompletableFuture

interface AddSensorReading {
    fun add(reading: Reading): CompletableFuture<ReadingId>

    class Default(private val pool: DbConnectionPool) : AddSensorReading {

        override fun add(reading: Reading): CompletableFuture<ReadingId> {
            return CoroutineScope(Dispatchers.IO).future {
                ReadingIdResponse(SensorReadingStorage(SensorReadingRepo(pool.get())).add(reading))
            }
        }
    }
}