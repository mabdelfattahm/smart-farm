package mofa.sf.usecase.reading

import mofa.sf.data.ReadingDataSource
import mofa.sf.domain.reading.*
import mofa.sf.domain.sensor.SensorId
import mofa.sf.usecase.reading.AddSensorReading
import mofa.sf.usecase.reading.RetrieveSensorReading

class SensorReadingStorage(private val ds: ReadingDataSource): AddSensorReading,
        RetrieveSensorReading, RetrieveAverageHumidity, RetrieveAverageMoisture, RetrieveAverageTemperature {
    override suspend fun add(reading: Reading) {
        this.ds.add(reading)
    }

    override suspend fun forSensor(id: SensorId): Collection<Reading> {
        return this.ds.findById(id)
    }

    override suspend fun averageHumidity(): Collection<Pair<Timestamp, Humidity>> {
        return this.ds.averageHumidity()
    }

    override suspend fun averageHumidity(from: Timestamp, to: Timestamp): Collection<Pair<Timestamp, Humidity>> {
        return this.ds.averageHumidity(from, to)
    }

    override suspend fun averageMoisture(): Collection<Pair<Timestamp, Moisture>> {
        return this.ds.averageMoisture()
    }

    override suspend fun averageMoisture(from: Timestamp, to: Timestamp): Collection<Pair<Timestamp, Moisture>> {
        return this.ds.averageMoisture(from, to)
    }

    override suspend fun averageTemperature(): Collection<Pair<Timestamp, Temperature>> {
        return this.ds.averageTemperature()
    }

    override suspend fun averageTemperature(from: Timestamp, to: Timestamp): Collection<Pair<Timestamp, Temperature>> {
        return this.ds.averageTemperature(from, to)
    }
}