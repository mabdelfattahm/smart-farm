package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.db.DbConnectionPool
import mofa.sf.db.h2.repository.ControlSignalRepo
import mofa.sf.domain.signal.Signal
import mofa.sf.domain.signal.SignalId
import mofa.sf.rest.dto.response.SignalIdResponse
import mofa.sf.usecase.signal.ControlSignalStorage
import java.util.concurrent.CompletableFuture

interface AddControlSignal {
    fun add(signal: Signal): CompletableFuture<SignalId>

    class Default(private val pool: DbConnectionPool) : AddControlSignal {

        override fun add(signal: Signal): CompletableFuture<SignalId> {
            return CoroutineScope(Dispatchers.IO).future {
                SignalIdResponse(ControlSignalStorage(ControlSignalRepo(pool.get())).add(signal))
            }
        }
    }
}