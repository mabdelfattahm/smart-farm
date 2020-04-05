package mofa.sf.postgres.repository

import kotlinx.coroutines.future.await
import mofa.sf.postgres.config.DbConnectionPool
import mofa.sf.data.ControllerDataSource
import mofa.sf.domain.controller.Controller
import mofa.sf.domain.controller.ControllerId
import mofa.sf.postgres.entity.ControllerEntity

class ControllerRepo(private val connection: DbConnectionPool) : ControllerDataSource {
    override suspend fun add(controller: Controller) {
        this.connection
            .get()
            .sendPreparedStatement(
                "INSERT INTO smart_farm.nodes_control(name, location, farm, status) VALUES(?, ?, ?, ?, ?)",
                arrayListOf(
                    controller.name(),
                    controller.location().asString(),
                    controller.farmId().asString(),
                    controller.status().ordinal
                )
            )
    }

    override suspend fun list(): List<Controller> {
        return this.connection
            .get()
            .sendQuery("SELECT * FROM smart_farm.nodes_control ORDER BY id")
            .await()
            .rows
            .map { ControllerEntity(it) }
    }

    override suspend fun list(page: Int, size: Int): List<Controller> {
        val offset = (page - 1) * size
        return this.connection
            .get()
            .sendQuery("SELECT * FROM smart_farm.nodes_control ORDER BY id OFFSET $offset LIMIT $size")
            .await()
            .rows
            .map { ControllerEntity(it) }
    }

    override suspend fun findById(id: ControllerId): Controller {
        return this.connection
            .get()
            .sendPreparedStatement("SELECT * FROM smart_farm.nodes_control WHERE id = ?", arrayListOf(id.asString()))
            .await()
            .rows
            .map { ControllerEntity(it) }
            .first()
    }
}