package mofa.sf.postgres.repository

import mofa.sf.data.ControllerDataSource
import mofa.sf.db.DbConnectionPool
import mofa.sf.domain.controller.Controller
import mofa.sf.domain.controller.ControllerId
import mofa.sf.postgres.entity.ControllerEntity
import mofa.sf.postgres.entity.ControllerIdEntity

class ControllerRepo(private val connection: DbConnectionPool) : ControllerDataSource {
    override suspend fun add(controller: Controller): ControllerId {
        return this.connection
            .get()
            .execute(
            "INSERT INTO smart_farm.nodes_control(name, location, farm, status) VALUES(?, ?, ?, ?)",
                controller.name().asString(),
                controller.location().asString(),
                controller.farmId().asString(),
                controller.status().asString()
            )
            .records()
            .map { ControllerIdEntity(it) }
            .first()
    }

    override suspend fun list(): List<Controller> {
        return this.connection
            .get()
            .execute("SELECT * FROM smart_farm.nodes_control ORDER BY id")
            .records()
            .map { ControllerEntity(it) }
    }

    override suspend fun list(page: Int, size: Int): List<Controller> {
        val offset = (page - 1) * size
        return this.connection
            .get()
            .execute("SELECT * FROM smart_farm.nodes_control ORDER BY id OFFSET $offset LIMIT $size")
            .records()
            .map { ControllerEntity(it) }
    }

    override suspend fun findById(id: ControllerId): Controller {
        return this.connection
            .get()
            .execute("SELECT * FROM smart_farm.nodes_control WHERE id = ?", id.asString())
            .records()
            .map { ControllerEntity(it) }
            .first()
    }
}