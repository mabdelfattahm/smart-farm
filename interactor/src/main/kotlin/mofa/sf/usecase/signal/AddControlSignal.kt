package mofa.sf.usecase.signal

import mofa.sf.domain.signal.Signal

interface AddControlSignal {
    suspend fun add(signal: Signal)
}