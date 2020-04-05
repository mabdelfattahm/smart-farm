package mofa.sf.postgres.repository

import com.github.jasync.sql.db.util.map
import kotlinx.coroutines.future.await
import mofa.sf.postgres.config.DbConnectionPool
import mofa.sf.data.ReadingDataSource
import mofa.sf.domain.reading.*
import mofa.sf.domain.sensor.SensorId
import mofa.sf.postgres.entity.ReadingEntity
import org.joda.time.DateTimeZone

class SensorReadingRepo(private val connection: DbConnectionPool) : ReadingDataSource {
    override suspend fun add(reading: Reading) {
        this.connection
            .get()
            .sendPreparedStatement(
                "INSERT INTO smart_farm.readings(time_stamp, temperature, humidity, moisture, sensor_node)" +
                " VALUES (?, ?, ?, ?, ?)",
                arrayListOf(
                    reading.timestamp(), reading.temperature(), reading.humidity(), reading.moisture(), reading.sensorId()
                )
            )
            .await()
    }

    override suspend fun findById(id: SensorId): Collection<Reading> {
        return this.connection
            .get()
            .sendPreparedStatement(
                "SELECT * FROM smart_farm.readings where sensor_node = ? ORDER BY time_stamp",
                arrayListOf(id.asString())
            )
            .await()
            .rows
            .map { ReadingEntity(it) }
    }

    override suspend fun averageHumidity(): Collection<Pair<Timestamp, Humidity>> {
        return this.connection
            .get()
            .sendQuery("SELECT time_stamp, AVG(humidity) AS humidity FROM smart_farm.readings GROUP BY time_stamp ORDER BY time_stamp")
            .await()
            .rows.map {
                Pair(
                    Timestamp.Default(it.getDate("time_stamp")!!.toDateTime(DateTimeZone.UTC).millis),
                    Humidity.Default(it.getDouble("humidity")!!)
                )
            }
    }

    override suspend fun averageHumidity(from: Timestamp, to: Timestamp): Collection<Pair<Timestamp, Humidity>> {
        return this.connection
            .get()
            .sendPreparedStatement(
                arrayListOf(
                    "SELECT time_stamp, AVG(humidity) AS humidity",
                    "FROM smart_farm.readings",
                    "WHERE time_stamp BETWEEN TO_TIMESTAMP(?) AND TO_TIMESTAMP(?) GROUP BY time_stamp ORDER BY time_stamp"
                ).joinToString(" "),
                arrayListOf(from.asLong(), to.asLong())
            )
            .await()
            .rows.map {
                Pair(
                    Timestamp.Default(it.getDate("time_stamp")!!.toDateTime(DateTimeZone.UTC).millis),
                    Humidity.Default(it.getDouble("humidity")!!)
                )
            }
    }

    override suspend fun averageMoisture(): Collection<Pair<Timestamp, Moisture>> {
        return this.connection
            .get()
            .sendQuery("SELECT time_stamp, AVG(moisture) AS moisture FROM smart_farm.readings GROUP BY time_stamp ORDER BY time_stamp")
            .await()
            .rows.map {
                Pair(
                    Timestamp.Default(it.getDate("time_stamp")!!.toDateTime(DateTimeZone.UTC).millis),
                    Moisture.Default(it.getDouble("moisture")!!)
                )
            }
    }

    override suspend fun averageMoisture(from: Timestamp, to: Timestamp): Collection<Pair<Timestamp, Moisture>> {
        return this.connection
            .get()
            .sendPreparedStatement(
                arrayListOf(
                    "SELECT time_stamp, AVG(moisture) AS moisture",
                    "FROM smart_farm.readings",
                    "WHERE time_stamp BETWEEN TO_TIMESTAMP(?) AND TO_TIMESTAMP(?) GROUP BY time_stamp ORDER BY time_stamp"
                ).joinToString(" "),
                arrayListOf(from.asLong(), to.asLong())
            )
            .await()
            .rows.map {
                Pair(
                    Timestamp.Default(it.getDate("time_stamp")!!.toDateTime(DateTimeZone.UTC).millis),
                    Moisture.Default(it.getDouble("moisture")!!)
                )
            }
    }

    override suspend fun averageTemperature(): Collection<Pair<Timestamp, Temperature>> {
        return this.connection
            .get()
            .sendQuery("SELECT time_stamp, AVG(temperature) AS temperature FROM smart_farm.readings GROUP BY time_stamp ORDER BY time_stamp")
            .await()
            .rows.map {
                Pair(
                    Timestamp.Default(it.getDate("time_stamp")!!.toDateTime(DateTimeZone.UTC).millis),
                    Temperature.Default(it.getDouble("temperature")!!)
                )
            }
    }

    override suspend fun averageTemperature(from: Timestamp, to: Timestamp): Collection<Pair<Timestamp, Temperature>> {
        return this.connection
            .get()
            .sendPreparedStatement(
                arrayListOf(
                    "SELECT time_stamp, AVG(temperature) AS temperature",
                    "FROM smart_farm.readings",
                    "WHERE time_stamp BETWEEN TO_TIMESTAMP(?) AND TO_TIMESTAMP(?) GROUP BY time_stamp ORDER BY time_stamp"
                ).joinToString(" "),
                arrayListOf(from.asLong(), to.asLong())
            ).await()
            .rows.map {
                Pair(
                    Timestamp.Default(it.getDate("time_stamp")!!.toDateTime(DateTimeZone.UTC).millis),
                    Temperature.Default(it.getDouble("temperature")!!)
                )
            }
    }
}