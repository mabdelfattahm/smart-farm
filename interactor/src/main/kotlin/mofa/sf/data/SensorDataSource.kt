package mofa.sf.data

import mofa.sf.domain.sensor.Sensor
import mofa.sf.domain.sensor.SensorId

interface SensorDataSource {
    suspend fun add(sensor: Sensor)
    suspend fun findById(id: SensorId): Sensor
    suspend fun list(): Collection<Sensor>
    suspend fun list(page: Int, size: Int): List<Sensor>
}