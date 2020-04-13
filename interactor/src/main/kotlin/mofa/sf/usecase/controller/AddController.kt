package mofa.sf.usecase.controller

import mofa.sf.domain.controller.Controller
import mofa.sf.domain.controller.ControllerId

interface AddController {
    suspend fun add(controller: Controller): ControllerId
}