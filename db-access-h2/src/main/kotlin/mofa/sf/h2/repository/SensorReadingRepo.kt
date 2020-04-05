package mofa.sf.h2.repository

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import mofa.sf.data.ReadingDataSource
import mofa.sf.domain.reading.*
import mofa.sf.domain.sensor.SensorId
import mofa.sf.h2.config.DbConnectionPool
import mofa.sf.h2.config.DbStatement
import mofa.sf.h2.entity.ReadingEntity
import java.time.LocalDateTime
import java.time.ZoneOffset

class SensorReadingRepo(private val pool: DbConnectionPool) : ReadingDataSource {
    override suspend fun add(reading: Reading) {
        DbStatement(this.pool)
            .execute(
                "INSERT INTO smart_farm.readings(time_stamp, temperature, humidity, moisture, sensor_node) VALUES (?, ?, ?, ?, ?)",
                    reading.timestamp(),
                    reading.temperature(),
                    reading.humidity(),
                    reading.moisture(), reading.sensorId()
            )
    }

    override suspend fun findById(id: SensorId): Collection<Reading> {
        return DbStatement(this.pool)
            .execute("SELECT * FROM smart_farm.readings where sensor_node = ? ORDER BY time_stamp", id.asString())
            .map {row, metadata ->  ReadingEntity(row, metadata) }
            .asFlow()
            .toList()
    }

    override suspend fun averageHumidity(): Collection<Pair<Timestamp, Humidity>> {
        return DbStatement(this.pool)
            .execute("SELECT time_stamp, AVG(humidity) AS humidity FROM smart_farm.readings GROUP BY time_stamp ORDER BY time_stamp")
            .map {row, metadata ->
                Pair(
                    Timestamp.Default(row.get("time_stamp", LocalDateTime::class.java)!!.toInstant(ZoneOffset.UTC).toEpochMilli()),
                    Humidity.Default(row.get("humidity", metadata.getColumnMetadata("humidity").javaType) as Double)
                )
            }
            .asFlow()
            .toList()
    }

    override suspend fun averageHumidity(from: Timestamp, to: Timestamp): Collection<Pair<Timestamp, Humidity>> {
        return DbStatement(this.pool)
            .execute(
            "SELECT time_stamp, AVG(humidity) AS humidity FROM smart_farm.readings" +
                    " WHERE time_stamp BETWEEN DATEADD('SECOND', ?, DATE '1970-01-01') AND DATEADD('SECOND', ?, DATE '1970-01-01')" +
                    " GROUP BY time_stamp ORDER BY time_stamp",
                from.asLong(),
                to.asLong()
            )
            .map {row, metadata ->
                Pair(
                    Timestamp.Default(row.get("time_stamp", LocalDateTime::class.java)!!.toInstant(ZoneOffset.UTC).toEpochMilli()),
                    Humidity.Default(row.get("humidity", metadata.getColumnMetadata("humidity").javaType) as Double)
                )
            }
            .asFlow()
            .toList()
    }

    override suspend fun averageMoisture(): Collection<Pair<Timestamp, Moisture>> {
        return DbStatement(this.pool)
            .execute("SELECT time_stamp, AVG(moisture) AS moisture FROM smart_farm.readings GROUP BY time_stamp ORDER BY time_stamp")
            .map {row, metadata ->
                Pair(
                    Timestamp.Default(row.get("time_stamp", LocalDateTime::class.java)!!.toInstant(ZoneOffset.UTC).toEpochMilli()),
                    Moisture.Default(row.get("moisture", metadata.getColumnMetadata("moisture").javaType) as Double)
                )
            }
            .asFlow()
            .toList()
    }

    override suspend fun averageMoisture(from: Timestamp, to: Timestamp): Collection<Pair<Timestamp, Moisture>> {
        return DbStatement(this.pool)
            .execute(
                "SELECT time_stamp, AVG(moisture) AS moisture FROM smart_farm.readings" +
                    " WHERE time_stamp BETWEEN DATEADD('SECOND', ?, DATE '1970-01-01') AND DATEADD('SECOND', ?, DATE '1970-01-01')" +
                    " GROUP BY time_stamp ORDER BY time_stamp",
                from.asLong(),
                to.asLong()
            )
            .map {row, metadata ->
                Pair(
                    Timestamp.Default(row.get("time_stamp", LocalDateTime::class.java)!!.toInstant(ZoneOffset.UTC).toEpochMilli()),
                    Moisture.Default(row.get("moisture", metadata.getColumnMetadata("moisture").javaType) as Double)
                )
            }
            .asFlow()
            .toList()
    }

    override suspend fun averageTemperature(): Collection<Pair<Timestamp, Temperature>> {
        return DbStatement(this.pool)
            .execute("SELECT time_stamp, AVG(temperature) AS temperature FROM smart_farm.readings GROUP BY time_stamp ORDER BY time_stamp")
            .map {row, metadata ->
                Pair(
                    Timestamp.Default(row.get("time_stamp", LocalDateTime::class.java)!!.toInstant(ZoneOffset.UTC).toEpochMilli()),
                    Temperature.Default(row.get("temperature", metadata.getColumnMetadata("temperature").javaType) as Double)
                )
            }
            .asFlow()
            .toList()
    }

    override suspend fun averageTemperature(from: Timestamp, to: Timestamp): Collection<Pair<Timestamp, Temperature>> {
        return DbStatement(this.pool)
            .execute(
                "SELECT time_stamp, AVG(temperature) AS temperature FROM smart_farm.readings" +
                    " WHERE time_stamp BETWEEN DATEADD('SECOND', ?, DATE '1970-01-01') AND DATEADD('SECOND', ?, DATE '1970-01-01')" +
                    " GROUP BY time_stamp ORDER BY time_stamp",
                    from.asLong(),
                    to.asLong()
            )
            .map {row, metadata ->
                Pair(
                    Timestamp.Default(row.get("time_stamp", LocalDateTime::class.java)!!.toInstant(ZoneOffset.UTC).toEpochMilli()),
                    Temperature.Default(row.get("temperature", metadata.getColumnMetadata("temperature").javaType) as Double)
                )
            }
            .asFlow()
            .toList()
    }
}