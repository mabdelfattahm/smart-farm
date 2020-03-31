package mofa.sf.data

import mofa.sf.domain.controller.ControllerId
import mofa.sf.domain.reading.Temperature
import mofa.sf.domain.signal.Control
import mofa.sf.domain.signal.Signal
import mofa.sf.domain.signal.Timestamp

interface SignalDataSource {
    suspend fun add(signal: Signal)
    suspend fun all(): Collection<Signal>
    suspend fun between(from: Timestamp, to: Timestamp): Collection<Signal>
    suspend fun findById(id: ControllerId): Collection<Signal>
    suspend fun findById(id: ControllerId, from: Timestamp, to: Timestamp): Collection<Signal>
    suspend fun signalWithTemperature(): Collection<Triple<Timestamp, Temperature, Control>>
    suspend fun signalWithTemperature(from: Timestamp, to: Timestamp): Collection<Triple<Timestamp, Temperature, Control>>
    suspend fun signalWithTemperature(id: ControllerId, from: Timestamp, to: Timestamp): Collection<Triple<Timestamp, Temperature, Control>>
}