package mofa.sf.usecase.reading

import mofa.sf.domain.reading.Temperature
import mofa.sf.domain.reading.Timestamp

interface RetrieveAverageTemperature {
    suspend fun averageTemperature(): Collection<Pair<Timestamp, Temperature>>
    suspend fun averageTemperature(from: Timestamp, to: Timestamp): Collection<Pair<Timestamp, Temperature>>
}