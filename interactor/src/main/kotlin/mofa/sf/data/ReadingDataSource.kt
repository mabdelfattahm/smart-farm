package mofa.sf.data

import mofa.sf.domain.reading.*
import mofa.sf.domain.sensor.SensorId

interface ReadingDataSource {
    suspend fun add(reading: Reading): ReadingId
    suspend fun findById(id: SensorId): Collection<Reading>
    suspend fun averageHumidity(): Collection<Pair<Timestamp, Humidity>>
    suspend fun averageHumidity(from: Timestamp, to: Timestamp): Collection<Pair<Timestamp, Humidity>>
    suspend fun averageMoisture(): Collection<Pair<Timestamp, Moisture>>
    suspend fun averageMoisture(from: Timestamp, to: Timestamp): Collection<Pair<Timestamp, Moisture>>
    suspend fun averageTemperature(): Collection<Pair<Timestamp, Temperature>>
    suspend fun averageTemperature(from: Timestamp, to: Timestamp): Collection<Pair<Timestamp, Temperature>>
}