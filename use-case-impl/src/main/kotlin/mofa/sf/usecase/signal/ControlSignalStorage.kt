package mofa.sf.usecase.signal

import mofa.sf.data.SignalDataSource
import mofa.sf.domain.controller.ControllerId
import mofa.sf.domain.reading.Temperature
import mofa.sf.domain.signal.Control
import mofa.sf.domain.signal.Signal
import mofa.sf.domain.signal.Timestamp
import mofa.sf.usecase.signal.AddControlSignal
import mofa.sf.usecase.signal.RetrieveControlSignal

class ControlSignalStorage(private val ds: SignalDataSource) : AddControlSignal, RetrieveControlSignal, RetrieveControlWithTemperature {
    override suspend fun add(signal: Signal) {
        this.ds.add(signal)
    }

    override suspend fun list(): Collection<Signal> {
        return this.ds.all()
    }

    override suspend fun list(from: Timestamp, to: Timestamp): Collection<Signal> {
        return this.ds.between(from, to)
    }

    override suspend fun forController(id: ControllerId): Collection<Signal> {
        return this.ds.findById(id)
    }

    override suspend fun signalWithTemperature(): Collection<Triple<Timestamp, Temperature, Control>> {
        return this.ds.signalWithTemperature()
    }

    override suspend fun signalWithTemperature(from: Timestamp, to: Timestamp): Collection<Triple<Timestamp, Temperature, Control>> {
        return this.ds.signalWithTemperature(from, to)
    }

    override suspend fun signalWithTemperature(id: ControllerId, from: Timestamp, to: Timestamp): Collection<Triple<Timestamp, Temperature, Control>> {
        return this.ds.signalWithTemperature(id, from, to)
    }
}