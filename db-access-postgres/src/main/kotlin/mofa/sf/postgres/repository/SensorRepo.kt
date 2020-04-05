package mofa.sf.postgres.repository

import mofa.sf.data.SensorDataSource
import mofa.sf.db.DbConnectionPool
import mofa.sf.domain.sensor.Sensor
import mofa.sf.domain.sensor.SensorId
import mofa.sf.postgres.entity.SensorEntity

class SensorRepo(private val connection: DbConnectionPool) : SensorDataSource {
    override suspend fun add(sensor: Sensor) {
        this.connection
            .get()
            .execute(
                "INSERT INTO smart_farm.nodes_sensor(name, location, farm, status) VALUES(?, ?, ?, ?, ?)",
                sensor.name(),
                sensor.location().asString(),
                sensor.farmId().asString(),
                sensor.status().ordinal
            )
    }

    override suspend fun list(): Collection<Sensor> {
        return this.connection
            .get()
            .execute("SELECT * FROM smart_farm.nodes_sensor ORDER BY id")
            .records()
            .map { SensorEntity(it) }
    }

    override suspend fun list(page: Int, size: Int): List<Sensor> {
        val offset = (page - 1) * size
        return this.connection
            .get()
            .execute("SELECT * FROM smart_farm.nodes_sensor ORDER BY id OFFSET $offset LIMIT $size")
            .records()
            .map { SensorEntity(it) }
    }

    override suspend fun findById(id: SensorId): Sensor {
        return this.connection
            .get()
            .execute("SELECT * FROM smart_farm.nodes_sensor WHERE id = ?", id.asString())
            .records()
            .map { SensorEntity(it) }
            .first()
    }
}