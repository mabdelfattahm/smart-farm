package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.db.DbConnectionPool
import mofa.sf.db.h2.repository.SensorRepo
import mofa.sf.domain.sensor.Sensor
import mofa.sf.rest.dto.response.SensorResponse
import mofa.sf.usecase.sensor.SensorStorage
import java.util.concurrent.CompletableFuture

interface GetSensorNode {
    fun list(): CompletableFuture<Collection<Sensor>>

    class Default(private val pool: DbConnectionPool) : GetSensorNode {
        override fun list(): CompletableFuture<Collection<Sensor>> {
            return CoroutineScope(Dispatchers.IO).future {
                SensorStorage(SensorRepo(pool.get())).list().map { SensorResponse(it) }
            }
        }
    }

    class Paged(private val pool: DbConnectionPool, private val page: Int, private val size: Int): GetSensorNode {
        init {
            if (page < 1) {
                throw IllegalArgumentException("Page cannot be less than 1")
            }
            if (size < 1) {
                throw IllegalArgumentException("Size cannot be less than 1")
            }
        }

        override fun list(): CompletableFuture<Collection<Sensor>> {
            return CoroutineScope(Dispatchers.IO).future {
                SensorStorage(SensorRepo(pool.get())).list(page, size).map { SensorResponse(it) }
            }
        }
    }
}