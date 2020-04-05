package mofa.sf.db.h2.entity

import mofa.sf.db.DbRecord
import mofa.sf.domain.reading.*
import mofa.sf.domain.sensor.SensorId
import java.time.ZoneOffset

class ReadingEntity(private val record: DbRecord): Reading {
    override fun id(): ReadingId {
        return ReadingId.Default(this.record.getInt("id"))
    }

    override fun sensorId(): SensorId {
        return SensorId.Default(this.record.getInt("sensor_node"))
    }

    override fun timestamp(): Timestamp {
        return Timestamp.Default(this.record.getLocalDate("time_stamp").toInstant(ZoneOffset.UTC).toEpochMilli())
    }

    override fun temperature(): Temperature {
        return Temperature.Default(this.record.getDouble("temperature"))
    }

    override fun humidity(): Humidity {
        return Humidity.Default(this.record.getDouble("humidity"))
    }

    override fun moisture(): Moisture {
        return Moisture.Default(this.record.getDouble("moisture"))
    }
}