package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.db.DbConnectionPool
import mofa.sf.db.h2.repository.ControllerRepo
import mofa.sf.domain.controller.Controller
import mofa.sf.domain.controller.ControllerId
import mofa.sf.rest.dto.response.ControllerIdResponse
import mofa.sf.usecase.controller.ControllerStorage
import java.util.concurrent.CompletableFuture

interface AddControlNode {
    fun add(controller: Controller): CompletableFuture<ControllerId>

    class Default(private val pool: DbConnectionPool) : AddControlNode {
        override fun add(controller: Controller): CompletableFuture<ControllerId> {
            return CoroutineScope(Dispatchers.IO).future {
                ControllerIdResponse(ControllerStorage(ControllerRepo(pool.get())).add(controller))
            }
        }
    }
}