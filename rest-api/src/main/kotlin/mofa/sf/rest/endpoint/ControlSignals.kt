package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.domain.controller.ControllerId
import mofa.sf.domain.signal.Signal
import mofa.sf.domain.signal.Timestamp
import mofa.sf.postgres.config.DbConnectionProvider
import mofa.sf.postgres.repository.ControlSignalRepo
import mofa.sf.rest.dto.SignalDto
import mofa.sf.usecase.signal.ControlSignalStorage
import java.time.Instant
import java.util.concurrent.CompletableFuture

interface ControlSignals {
    fun list(): CompletableFuture<Collection<Signal>>

    class Default(private val provider: DbConnectionProvider): ControlSignals {
        override fun list(): CompletableFuture<Collection<Signal>> {
            return CoroutineScope(Dispatchers.IO).future {
                ControlSignalStorage(ControlSignalRepo(provider)).list().map { SignalDto(it) }
            }
        }
    }

    class FilteredByTime(private val provider: DbConnectionProvider, private val from: Long, private val to: Long): ControlSignals {
        init {
            if (Instant.ofEpochMilli(this.to).isBefore(Instant.ofEpochMilli(this.from))) {
                throw IllegalArgumentException("To must be after From")
            }
        }

        override fun list(): CompletableFuture<Collection<Signal>> {
            val ts = Timestamp.Default(this.from)
            val other = Timestamp.Default(this.to)
            return CoroutineScope(Dispatchers.IO).future {
                ControlSignalStorage(ControlSignalRepo(provider)).list(ts, other).map { SignalDto(it) }
            }
        }
    }

    class FilteredBySensor(private val provider: DbConnectionProvider, private val id: Int): ControlSignals {
        init {
            if (this.id < 1) {
                throw IllegalArgumentException("Sensor id must be > 0")
            }
        }

        override fun list(): CompletableFuture<Collection<Signal>> {
            val controller = ControllerId.Default(this.id)
            return CoroutineScope(Dispatchers.IO).future {
                ControlSignalStorage(ControlSignalRepo(provider)).forController(controller).map { SignalDto(it) }
            }
        }
    }
}