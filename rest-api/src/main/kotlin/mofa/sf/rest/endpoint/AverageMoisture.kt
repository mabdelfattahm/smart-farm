package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.domain.reading.Timestamp
import mofa.sf.db.DbConnectionPool
import mofa.sf.db.h2.repository.SensorReadingRepo
import mofa.sf.rest.dto.AverageMoistureDto
import mofa.sf.usecase.reading.SensorReadingStorage
import java.time.Instant
import java.util.concurrent.CompletableFuture

interface AverageMoisture {
    fun list(): CompletableFuture<Collection<AverageMoistureDto>>

    class Default(private val pool: DbConnectionPool): AverageMoisture {
        override fun list(): CompletableFuture<Collection<AverageMoistureDto>> {
            return CoroutineScope(Dispatchers.IO).future {
                SensorReadingStorage(SensorReadingRepo(pool.get())).averageMoisture().map { AverageMoistureDto(it) }
            }
        }
    }

    class FilteredByTime(private val pool: DbConnectionPool, private val from: Long, private val to: Long): AverageMoisture {
        init {
            if (Instant.ofEpochSecond(this.to).isBefore(Instant.ofEpochSecond(this.from))) {
                throw IllegalArgumentException("To must be after From")
            }
        }

        override fun list(): CompletableFuture<Collection<AverageMoistureDto>> {
            val ts = Timestamp.Default(this.from)
            val other = Timestamp.Default(this.to)
            return CoroutineScope(Dispatchers.IO).future {
                SensorReadingStorage(SensorReadingRepo(pool.get())).averageMoisture(ts, other).map { AverageMoistureDto(it) }
            }
        }
    }
}