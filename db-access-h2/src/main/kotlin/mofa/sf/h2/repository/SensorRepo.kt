package mofa.sf.h2.repository

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import mofa.sf.data.SensorDataSource
import mofa.sf.domain.sensor.Sensor
import mofa.sf.domain.sensor.SensorId
import mofa.sf.h2.config.DbConnectionPool
import mofa.sf.h2.config.DbStatement
import mofa.sf.h2.entity.SensorEntity

class SensorRepo(private val pool: DbConnectionPool) : SensorDataSource {
    override suspend fun add(sensor: Sensor) {
        DbStatement(this.pool)
            .execute(
                "INSERT INTO smart_farm.nodes_sensor(name, location, farm, status) VALUES(?, ?, ?, ?, ?)",
                    sensor.name(),
                    sensor.location().asString(),
                    sensor.farmId().asString(),
                    sensor.status().ordinal
            )
    }

    override suspend fun list(): Collection<Sensor> {
        return DbStatement(this.pool)
            .execute("SELECT * FROM smart_farm.nodes_sensor ORDER BY id")
            .map { row, _ -> SensorEntity(row) }
            .asFlow()
            .toList()
    }

    override suspend fun list(page: Int, size: Int): List<Sensor> {
        val offset = (page - 1) * size
        return DbStatement(this.pool)
            .execute("SELECT * FROM smart_farm.nodes_sensor ORDER BY id OFFSET $offset LIMIT $size")
            .map { row, _ -> SensorEntity(row) }
            .asFlow()
            .toList()
    }

    override suspend fun findById(id: SensorId): Sensor {
        return DbStatement(this.pool)
            .execute("SELECT * FROM smart_farm.nodes_sensor WHERE id = ?", id.asString())
            .map { row, _ -> SensorEntity(row) }
            .awaitFirst()
    }
}