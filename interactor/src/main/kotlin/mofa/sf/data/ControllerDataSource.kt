package mofa.sf.data

import mofa.sf.domain.controller.Controller
import mofa.sf.domain.controller.ControllerId

interface ControllerDataSource {
    suspend fun add(controller: Controller)
    suspend fun findById(id: ControllerId): Controller
    suspend fun list(): List<Controller>
    suspend fun list(page: Int, size: Int): List<Controller>
}