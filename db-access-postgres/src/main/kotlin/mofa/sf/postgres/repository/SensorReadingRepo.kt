package mofa.sf.postgres.repository

import mofa.sf.data.ReadingDataSource
import mofa.sf.db.DbConnectionPool
import mofa.sf.domain.reading.*
import mofa.sf.domain.sensor.SensorId
import mofa.sf.postgres.entity.ReadingEntity
import java.time.ZoneOffset

class SensorReadingRepo(private val connection: DbConnectionPool) : ReadingDataSource {
    override suspend fun add(reading: Reading) {
        this.connection
            .get()
            .execute(
                "INSERT INTO smart_farm.readings(time_stamp, temperature, humidity, moisture, sensor_node) VALUES (?, ?, ?, ?, ?)", 
                reading.timestamp(),
                reading.temperature(),
                reading.humidity(),
                reading.moisture(),
                reading.sensorId()
            )
            .records()
    }

    override suspend fun findById(id: SensorId): Collection<Reading> {
        return this.connection
            .get()
            .execute("SELECT * FROM smart_farm.readings where sensor_node = ? ORDER BY time_stamp", id.asString())
            .records()
            .map { ReadingEntity(it) }
    }

    override suspend fun averageHumidity(): Collection<Pair<Timestamp, Humidity>> {
        return this.connection
            .get()
            .execute("SELECT time_stamp, AVG(humidity) AS humidity FROM smart_farm.readings GROUP BY time_stamp ORDER BY time_stamp")
           .records()
            .map {
                Pair(
                    Timestamp.Default(it.getLocalDate("time_stamp").toInstant(ZoneOffset.UTC).toEpochMilli()),
                    Humidity.Default(it.getDouble("humidity"))
                )
            }
    }

    override suspend fun averageHumidity(from: Timestamp, to: Timestamp): Collection<Pair<Timestamp, Humidity>> {
        return this.connection
            .get()
            .execute(
                "SELECT time_stamp, AVG(humidity) AS humidity"
                    + " FROM smart_farm.readings"
                    + " WHERE time_stamp BETWEEN TO_TIMESTAMP(?) AND TO_TIMESTAMP(?) GROUP BY time_stamp ORDER BY time_stamp",
                from.asLong(),
                to.asLong()
            )
            .records()
            .map {
                Pair(
                    Timestamp.Default(it.getLocalDate("time_stamp").toInstant(ZoneOffset.UTC).toEpochMilli()),
                    Humidity.Default(it.getDouble("humidity"))
                )
            }
    }

    override suspend fun averageMoisture(): Collection<Pair<Timestamp, Moisture>> {
        return this.connection
            .get()
            .execute("SELECT time_stamp, AVG(moisture) AS moisture FROM smart_farm.readings GROUP BY time_stamp ORDER BY time_stamp")
            .records()
            .map {
                Pair(
                    Timestamp.Default(it.getLocalDate("time_stamp").toInstant(ZoneOffset.UTC).toEpochMilli()),
                    Moisture.Default(it.getDouble("moisture"))
                )
            }
    }

    override suspend fun averageMoisture(from: Timestamp, to: Timestamp): Collection<Pair<Timestamp, Moisture>> {
        return this.connection
            .get()
            .execute(
                "SELECT time_stamp, AVG(moisture) AS moisture"
                    + " FROM smart_farm.readings"
                    + " WHERE time_stamp BETWEEN TO_TIMESTAMP(?) AND TO_TIMESTAMP(?) GROUP BY time_stamp ORDER BY time_stamp",
                from.asLong(),
                to.asLong()
            )
            .records()
            .map {
                Pair(
                    Timestamp.Default(it.getLocalDate("time_stamp").toInstant(ZoneOffset.UTC).toEpochMilli()),
                    Moisture.Default(it.getDouble("moisture"))
                )
            }
    }

    override suspend fun averageTemperature(): Collection<Pair<Timestamp, Temperature>> {
        return this.connection
            .get()
            .execute("SELECT time_stamp, AVG(temperature) AS temperature FROM smart_farm.readings GROUP BY time_stamp ORDER BY time_stamp")
            .records()
            .map {
                Pair(
                    Timestamp.Default(it.getLocalDate("time_stamp").toInstant(ZoneOffset.UTC).toEpochMilli()),
                    Temperature.Default(it.getDouble("temperature"))
                )
            }
    }

    override suspend fun averageTemperature(from: Timestamp, to: Timestamp): Collection<Pair<Timestamp, Temperature>> {
        return this.connection
            .get()
            .execute(
                "SELECT time_stamp, AVG(temperature) AS temperature" 
                    + " FROM smart_farm.readings" 
                    + " WHERE time_stamp BETWEEN TO_TIMESTAMP(?) AND TO_TIMESTAMP(?) GROUP BY time_stamp ORDER BY time_stamp",
                from.asLong(),
                to.asLong()
            )
            .records()
            .map {
                Pair(
                    Timestamp.Default(it.getLocalDate("time_stamp").toInstant(ZoneOffset.UTC).toEpochMilli()),
                    Temperature.Default(it.getDouble("temperature"))
                )
            }
    }
}