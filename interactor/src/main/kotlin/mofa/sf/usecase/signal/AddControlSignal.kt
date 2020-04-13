package mofa.sf.usecase.signal

import mofa.sf.domain.signal.Signal
import mofa.sf.domain.signal.SignalId

interface AddControlSignal {
    suspend fun add(signal: Signal): SignalId
}