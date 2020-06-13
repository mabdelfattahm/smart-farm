package mofa.sf.usecase.signal

import mofa.sf.domain.controller.ControllerId
import mofa.sf.domain.signal.Signal
import mofa.sf.domain.signal.Timestamp

interface RetrieveControlSignal {
    suspend fun all(): Collection<Signal>
    suspend fun forController(id: ControllerId): Collection<Signal>
    suspend fun list(from: Timestamp, to: Timestamp): Collection<Signal>
}