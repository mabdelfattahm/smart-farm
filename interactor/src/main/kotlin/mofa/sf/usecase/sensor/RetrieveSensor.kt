package mofa.sf.usecase.sensor

import mofa.sf.domain.sensor.Sensor

interface RetrieveSensor {
    suspend fun list(): Collection<Sensor>
    suspend fun list(page: Int, size: Int): Collection<Sensor>
}