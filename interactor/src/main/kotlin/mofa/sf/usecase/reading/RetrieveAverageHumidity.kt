package mofa.sf.usecase.reading

import mofa.sf.domain.reading.Humidity
import mofa.sf.domain.reading.Timestamp

interface RetrieveAverageHumidity {
    suspend fun averageHumidity(): Collection<Pair<Timestamp, Humidity>>
    suspend fun averageHumidity(from: Timestamp, to: Timestamp): Collection<Pair<Timestamp, Humidity>>
}