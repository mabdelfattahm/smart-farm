package mofa.sf.usecase.controller

import mofa.sf.data.ControllerDataSource
import mofa.sf.domain.controller.Controller
import mofa.sf.domain.controller.ControllerId

class ControllerStorage(private val ds: ControllerDataSource): AddController, RetrieveController {
    override suspend fun add(controller: Controller): ControllerId {
        return this.ds.add(controller)
    }

    override suspend fun list(): Collection<Controller> {
        return this.ds.list()
    }

    override suspend fun list(page: Int, size: Int): Collection<Controller> {
        return this.ds.list(page, size)
    }
}