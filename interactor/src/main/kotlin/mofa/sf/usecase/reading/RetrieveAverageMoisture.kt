package mofa.sf.usecase.reading

import mofa.sf.domain.reading.Moisture
import mofa.sf.domain.reading.Timestamp

interface RetrieveAverageMoisture {
    suspend fun averageMoisture(): Collection<Pair<Timestamp, Moisture>>
    suspend fun averageMoisture(from: Timestamp, to: Timestamp): Collection<Pair<Timestamp, Moisture>>
}