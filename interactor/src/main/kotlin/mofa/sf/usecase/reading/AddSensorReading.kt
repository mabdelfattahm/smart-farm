package mofa.sf.usecase.reading

import mofa.sf.domain.reading.Reading

interface AddSensorReading {
    suspend fun add(reading: Reading)
}