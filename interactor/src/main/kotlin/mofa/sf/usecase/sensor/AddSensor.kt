package mofa.sf.usecase.sensor

import mofa.sf.domain.sensor.Sensor
import mofa.sf.domain.sensor.SensorId

interface AddSensor {
    suspend fun add(sensor: Sensor): SensorId
}