package mofa.sf.h2.repository

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import mofa.sf.data.ControllerDataSource
import mofa.sf.domain.controller.Controller
import mofa.sf.domain.controller.ControllerId
import mofa.sf.h2.config.DbConnectionPool
import mofa.sf.h2.config.DbStatement
import mofa.sf.h2.entity.ControllerEntity

class ControllerRepo(private val pool: DbConnectionPool) : ControllerDataSource {
    override suspend fun add(controller: Controller) {
        DbStatement(this.pool)
            .execute(
                "INSERT INTO smart_farm.nodes_control(name, location, farm, status) VALUES(?, ?, ?, ?, ?)",
                controller.name(),
                controller.location().asString(),
                controller.farmId().asString(),
                controller.status().ordinal
            )
    }

    override suspend fun list(): List<Controller> {
        return DbStatement(this.pool)
            .execute("SELECT * FROM smart_farm.nodes_control ORDER BY id")
            .map { row, _ ->  ControllerEntity(row) }
            .asFlow()
            .toList()
    }

    override suspend fun list(page: Int, size: Int): List<Controller> {
        val offset = (page - 1) * size
        return DbStatement(this.pool)
            .execute("SELECT * FROM smart_farm.nodes_control ORDER BY id OFFSET $offset LIMIT $size")
            .map { row, _ -> ControllerEntity(row) }
            .asFlow()
            .toList()
    }

    override suspend fun findById(id: ControllerId): Controller {
        return DbStatement(this.pool)
            .execute("SELECT * FROM smart_farm.nodes_control WHERE id = ?", id.asString())
            .map { row, _ -> ControllerEntity(row) }
            .awaitFirst()
    }
}