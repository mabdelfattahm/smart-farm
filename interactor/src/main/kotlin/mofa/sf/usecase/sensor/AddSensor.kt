package mofa.sf.usecase.sensor

import mofa.sf.domain.sensor.Sensor

interface AddSensor {
    suspend fun add(sensor: Sensor)
}