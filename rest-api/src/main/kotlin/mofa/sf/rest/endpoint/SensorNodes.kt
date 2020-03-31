package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.postgres.config.DbConnectionProvider
import mofa.sf.domain.sensor.Sensor
import mofa.sf.postgres.repository.SensorRepo
import mofa.sf.rest.dto.SensorDto
import mofa.sf.usecase.sensor.SensorStorage
import java.util.concurrent.CompletableFuture

interface SensorNodes {
    fun list(): CompletableFuture<Collection<Sensor>>

    class Default(private val provider: DbConnectionProvider) : SensorNodes {
        override fun list(): CompletableFuture<Collection<Sensor>> {
            return CoroutineScope(Dispatchers.IO).future {
                SensorStorage(SensorRepo(provider)).list().map { SensorDto(it) }
            }
        }
    }

    class Paged(private val provider: DbConnectionProvider, private val page: Int, private val size: Int): SensorNodes {
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
                SensorStorage(SensorRepo(provider)).list(page, size).map { SensorDto(it) }
            }
        }
    }
}