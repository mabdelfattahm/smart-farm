package mofa.sf.usecase.reading

import mofa.sf.domain.reading.Reading
import mofa.sf.domain.reading.Timestamp
import mofa.sf.domain.sensor.SensorId

interface RetrieveSensorReading {
    suspend fun forSensor(id: SensorId): Collection<Reading>
}