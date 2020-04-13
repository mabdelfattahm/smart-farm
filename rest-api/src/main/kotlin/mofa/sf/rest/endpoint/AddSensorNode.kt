package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.db.DbConnectionPool
import mofa.sf.db.h2.repository.SensorRepo
import mofa.sf.domain.sensor.Sensor
import mofa.sf.domain.sensor.SensorId
import mofa.sf.rest.dto.response.SensorIdResponse
import mofa.sf.usecase.sensor.SensorStorage
import java.util.concurrent.CompletableFuture

interface AddSensorNode {
    fun add(sensor: Sensor): CompletableFuture<SensorId>

    class Default(private val pool: DbConnectionPool) : AddSensorNode {
        override fun add(sensor: Sensor): CompletableFuture<SensorId> {
            return CoroutineScope(Dispatchers.IO).future {
                SensorIdResponse(SensorStorage(SensorRepo(pool.get())).add(sensor))
            }
        }
    }
}