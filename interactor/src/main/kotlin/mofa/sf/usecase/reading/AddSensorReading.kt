package mofa.sf.usecase.reading

import mofa.sf.domain.reading.Reading
import mofa.sf.domain.reading.ReadingId

interface AddSensorReading {
    suspend fun add(reading: Reading): ReadingId
}