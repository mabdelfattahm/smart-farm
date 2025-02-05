package mofa.sf.usecase.sensor

import mofa.sf.data.SensorDataSource
import mofa.sf.domain.sensor.Sensor
import mofa.sf.domain.sensor.SensorId

class SensorStorage(private val ds: SensorDataSource) : AddSensor, RetrieveSensor {
    override suspend fun add(sensor: Sensor): SensorId {
        return this.ds.add(sensor)
    }

    override suspend fun list(): Collection<Sensor> {
        return this.ds.list()
    }

    override suspend fun list(page: Int, size: Int): Collection<Sensor> {
        return this.ds.list(page, size)
    }
}