package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.db.DbConnectionPool
import mofa.sf.db.h2.repository.ControlSignalRepo
import mofa.sf.domain.controller.ControllerId
import mofa.sf.domain.signal.Signal
import mofa.sf.domain.signal.Timestamp
import mofa.sf.rest.dto.response.SignalResponse
import mofa.sf.usecase.signal.ControlSignalStorage
import java.time.Instant
import java.util.concurrent.CompletableFuture

interface GetControlSignal {
    fun signals(): CompletableFuture<Collection<Signal>>

    class Default(private val pool: DbConnectionPool): GetControlSignal {
        override fun signals(): CompletableFuture<Collection<Signal>> {
            return CoroutineScope(Dispatchers.IO).future {
                ControlSignalStorage(ControlSignalRepo(pool.get())).all().map { SignalResponse(it) }
            }
        }
    }

    class FilteredByTime(private val pool: DbConnectionPool, private val from: Long, private val to: Long): GetControlSignal {
        init {
            if (Instant.ofEpochSecond(this.to).isBefore(Instant.ofEpochSecond(this.from))) {
                throw IllegalArgumentException("To must be after From")
            }
        }

        override fun signals(): CompletableFuture<Collection<Signal>> {
            val ts = Timestamp.Default(this.from)
            val other = Timestamp.Default(this.to)
            return CoroutineScope(Dispatchers.IO).future {
                ControlSignalStorage(ControlSignalRepo(pool.get())).list(ts, other).map { SignalResponse(it) }
            }
        }
    }

    class FilteredBySensor(private val pool: DbConnectionPool, private val id: Int): GetControlSignal {
        init {
            if (this.id < 1) {
                throw IllegalArgumentException("Sensor id must be > 0")
            }
        }

        override fun signals(): CompletableFuture<Collection<Signal>> {
            val controller = ControllerId.Default(this.id)
            return CoroutineScope(Dispatchers.IO).future {
                ControlSignalStorage(ControlSignalRepo(pool.get())).forController(controller).map { SignalResponse(it) }
            }
        }
    }
}