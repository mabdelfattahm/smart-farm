package mofa.sf.db.h2.repository

import mofa.sf.data.SensorDataSource
import mofa.sf.db.DbConnection
import mofa.sf.db.h2.entity.SensorEntity
import mofa.sf.db.h2.entity.SensorIdEntity
import mofa.sf.domain.sensor.Sensor
import mofa.sf.domain.sensor.SensorId

class SensorRepo(private val connection: DbConnection) : SensorDataSource {
    override suspend fun add(sensor: Sensor): SensorId {
        return this.connection
            .execute(
                "INSERT INTO smart_farm.nodes_sensor(name, location, farm, status) VALUES(?, ?, ?, ?)",
                    sensor.name().asString(),
                    sensor.location().asString(),
                    sensor.farmId().asString(),
                    sensor.status().asString()
            )
            .records()
            .map { SensorIdEntity(it) }
            .first()
    }

    override suspend fun list(): Collection<Sensor> {
        return this.connection
            .execute("SELECT * FROM smart_farm.nodes_sensor ORDER BY id")
            .records()
            .map { SensorEntity(it) }
    }

    override suspend fun list(page: Int, size: Int): List<Sensor> {
        val offset = (page - 1) * size
        return this.connection
            .execute("SELECT * FROM smart_farm.nodes_sensor ORDER BY id OFFSET $offset LIMIT $size")
            .records()
            .map { SensorEntity(it) }
    }

    override suspend fun findById(id: SensorId): Sensor {
        return this.connection
            .execute("SELECT * FROM smart_farm.nodes_sensor WHERE id = ?", id.asString())
            .records()
            .map { SensorEntity(it) }
            .first()
    }
}