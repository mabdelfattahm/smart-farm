package mofa.sf.usecase.controller

import mofa.sf.domain.controller.Controller

interface RetrieveController {
    suspend fun list(): Collection<Controller>
    suspend fun list(page: Int, size: Int): Collection<Controller>
}