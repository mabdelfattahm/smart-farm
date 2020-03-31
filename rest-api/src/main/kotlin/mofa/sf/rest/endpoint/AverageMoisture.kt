package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.domain.reading.Timestamp
import mofa.sf.postgres.config.DbConnectionProvider
import mofa.sf.postgres.repository.SensorReadingRepo
import mofa.sf.rest.dto.AverageMoistureDto
import mofa.sf.usecase.reading.SensorReadingStorage
import java.time.Instant
import java.util.concurrent.CompletableFuture

interface AverageMoisture {
    fun list(): CompletableFuture<Collection<AverageMoistureDto>>

    class Default(private val provider: DbConnectionProvider): AverageMoisture {
        override fun list(): CompletableFuture<Collection<AverageMoistureDto>> {
            return CoroutineScope(Dispatchers.IO).future {
                SensorReadingStorage(SensorReadingRepo(provider)).averageMoisture().map { AverageMoistureDto(it) }
            }
        }
    }

    class FilteredByTime(private val provider: DbConnectionProvider, private val from: Long, private val to: Long): AverageMoisture {
        init {
            if (Instant.ofEpochMilli(this.to).isBefore(Instant.ofEpochMilli(this.from))) {
                throw IllegalArgumentException("To must be after From")
            }
        }

        override fun list(): CompletableFuture<Collection<AverageMoistureDto>> {
            val ts = Timestamp.Default(this.from)
            val other = Timestamp.Default(this.to)
            return CoroutineScope(Dispatchers.IO).future {
                SensorReadingStorage(SensorReadingRepo(provider)).averageMoisture(ts, other).map { AverageMoistureDto(it) }
            }
        }
    }
}