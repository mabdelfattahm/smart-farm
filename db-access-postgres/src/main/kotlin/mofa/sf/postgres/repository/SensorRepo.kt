package mofa.sf.postgres.repository

import kotlinx.coroutines.future.await
import mofa.sf.postgres.config.DbConnectionPool
import mofa.sf.data.SensorDataSource
import mofa.sf.domain.sensor.Sensor
import mofa.sf.domain.sensor.SensorId
import mofa.sf.postgres.entity.SensorEntity

class SensorRepo(private val connection: DbConnectionPool) : SensorDataSource {
    override suspend fun add(sensor: Sensor) {
        this.connection
            .get()
            .sendPreparedStatement(
                "INSERT INTO smart_farm.nodes_sensor(name, location, farm, status) VALUES(?, ?, ?, ?, ?)",
                arrayListOf(
                    sensor.name(),
                    sensor.location().asString(),
                    sensor.farmId().asString(),
                    sensor.status().ordinal
                )
            )
    }

    override suspend fun list(): Collection<Sensor> {
        return this.connection
            .get()
            .sendQuery("SELECT * FROM smart_farm.nodes_sensor ORDER BY id")
            .await()
            .rows
            .map { SensorEntity(it) }
    }

    override suspend fun list(page: Int, size: Int): List<Sensor> {
        val offset = (page - 1) * size
        return this.connection
            .get()
            .sendQuery("SELECT * FROM smart_farm.nodes_sensor ORDER BY id OFFSET $offset LIMIT $size")
            .await()
            .rows
            .map { SensorEntity(it) }
    }

    override suspend fun findById(id: SensorId): Sensor {
        return this.connection
            .get()
            .sendPreparedStatement("SELECT * FROM smart_farm.nodes_sensor WHERE id = ?", arrayListOf(id.asString()))
            .await()
            .rows
            .map { SensorEntity(it) }
            .first()
    }
}