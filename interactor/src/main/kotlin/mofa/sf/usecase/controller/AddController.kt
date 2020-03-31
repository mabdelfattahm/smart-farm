package mofa.sf.usecase.controller

import mofa.sf.domain.controller.Controller

interface AddController {
    suspend fun add(controller: Controller)
}