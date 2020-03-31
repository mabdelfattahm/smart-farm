package mofa.sf.domain.reading

import mofa.sf.domain.sensor.SensorId

interface Reading {
    fun id(): ReadingId
    fun sensorId(): SensorId
    fun timestamp(): Timestamp
    fun temperature(): Temperature
    fun humidity(): Humidity
    fun moisture(): Moisture
}