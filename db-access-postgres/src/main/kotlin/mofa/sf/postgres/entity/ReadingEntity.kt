package mofa.sf.postgres.entity

import mofa.sf.db.DbRecord
import mofa.sf.domain.reading.Humidity
import mofa.sf.domain.reading.Moisture
import mofa.sf.domain.reading.Reading
import mofa.sf.domain.reading.ReadingId
import mofa.sf.domain.reading.Temperature
import mofa.sf.domain.reading.Timestamp
import mofa.sf.domain.sensor.SensorId
import java.time.ZoneOffset

class ReadingEntity(private val data: DbRecord): Reading {
    override fun id(): ReadingId {
        return ReadingId.Default(this.data.getInt("id"))
    }

    override fun sensorId(): SensorId {
        return SensorId.Default(this.data.getInt("node"))
    }

    override fun timestamp(): Timestamp {
        return Timestamp.Default(this.data.getLocalDate("time_stamp").toInstant(ZoneOffset.UTC).toEpochMilli())
    }

    override fun temperature(): Temperature {
        return Temperature.Default(this.data.getDouble("temperature"))
    }

    override fun humidity(): Humidity {
        return Humidity.Default(this.data.getDouble("temperature"))
    }

    override fun moisture(): Moisture {
        return Moisture.Default(this.data.getDouble("temperature"))
    }
}