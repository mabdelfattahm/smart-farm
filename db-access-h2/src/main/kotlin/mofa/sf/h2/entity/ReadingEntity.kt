package mofa.sf.h2.entity

import io.r2dbc.spi.Row
import io.r2dbc.spi.RowMetadata
import mofa.sf.domain.reading.*
import mofa.sf.domain.sensor.SensorId
import java.time.LocalDateTime
import java.time.ZoneOffset

class ReadingEntity(private val data: Row, private val metadata: RowMetadata): Reading {
    override fun id(): ReadingId {
        return ReadingId.Default(this.data.get("id", metadata.getColumnMetadata("id").javaType) as Int)
    }

    override fun sensorId(): SensorId {
        return SensorId.Default(this.data.get("sensor_node", metadata.getColumnMetadata("sensor_node").javaType) as Int)
    }

    override fun timestamp(): Timestamp {
        return Timestamp.Default(this.data.get("time_stamp", LocalDateTime::class.java)!!.toInstant(ZoneOffset.UTC).toEpochMilli())
    }

    override fun temperature(): Temperature {
        return Temperature.Default(this.data.get("temperature", this.metadata.getColumnMetadata("temperature").javaType) as Double)
    }

    override fun humidity(): Humidity {
        return Humidity.Default(this.data.get("humidity", this.metadata.getColumnMetadata("hymidity").javaType) as Double)
    }

    override fun moisture(): Moisture {
        return Moisture.Default(this.data.get("moisture", this.metadata.getColumnMetadata("moisture").javaType) as Double)
    }
}