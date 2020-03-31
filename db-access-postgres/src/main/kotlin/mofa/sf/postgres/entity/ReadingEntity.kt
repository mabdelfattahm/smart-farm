package mofa.sf.postgres.entity

import com.github.jasync.sql.db.RowData
import mofa.sf.domain.reading.Humidity
import mofa.sf.domain.reading.Moisture
import mofa.sf.domain.reading.Reading
import mofa.sf.domain.reading.ReadingId
import mofa.sf.domain.reading.Temperature
import mofa.sf.domain.reading.Timestamp
import mofa.sf.domain.sensor.SensorId
import org.joda.time.DateTimeZone

class ReadingEntity(private val data: RowData): Reading {
    override fun id(): ReadingId {
        return ReadingId.Default(this.data.getInt("id")!!)
    }

    override fun sensorId(): SensorId {
        return SensorId.Default(this.data.getInt("node")!!)
    }

    override fun timestamp(): Timestamp {
        return Timestamp.Default(this.data.getDate("time_stamp")!!.toDateTime(DateTimeZone.UTC).millis)
    }

    override fun temperature(): Temperature {
        return Temperature.Default(this.data.getDouble("temperature")!!)
    }

    override fun humidity(): Humidity {
        return Humidity.Default(this.data.getDouble("temperature")!!)
    }

    override fun moisture(): Moisture {
        return Moisture.Default(this.data.getDouble("temperature")!!)
    }
}