package mofa.sf.usecase.signal

import mofa.sf.domain.controller.ControllerId
import mofa.sf.domain.reading.Temperature
import mofa.sf.domain.signal.Control
import mofa.sf.domain.signal.Timestamp

interface RetrieveControlWithTemperature {
    suspend fun signalWithTemperature(): Collection<Triple<Timestamp, Temperature, Control>>
    suspend fun signalWithTemperature(from: Timestamp, to: Timestamp): Collection<Triple<Timestamp, Temperature, Control>>
    suspend fun signalWithTemperature(id: ControllerId, from: Timestamp, to: Timestamp): Collection<Triple<Timestamp, Temperature, Control>>
}