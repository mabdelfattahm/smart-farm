package mofa.sf.db.h2.repository

import mofa.sf.data.ControllerDataSource
import mofa.sf.db.DbConnection
import mofa.sf.db.h2.entity.ControllerEntity
import mofa.sf.db.h2.entity.ControllerIdEntity
import mofa.sf.domain.controller.Controller
import mofa.sf.domain.controller.ControllerId

class ControllerRepo(private val connection: DbConnection) : ControllerDataSource {
    override suspend fun add(controller: Controller): ControllerId {
        return this.connection
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
            .execute("SELECT * FROM smart_farm.nodes_control ORDER BY id")
            .records()
            .map { ControllerEntity(it) }
    }

    override suspend fun list(page: Int, size: Int): List<Controller> {
        val offset = (page - 1) * size
        return this.connection
            .execute("SELECT * FROM smart_farm.nodes_control ORDER BY id OFFSET $offset LIMIT $size")
            .records()
            .map { ControllerEntity(it) }
    }

    override suspend fun findById(id: ControllerId): Controller {
        return this.connection
            .execute("SELECT * FROM smart_farm.nodes_control WHERE id = ?", id.asString())
            .records()
            .map { ControllerEntity(it) }
            .first()
    }
}