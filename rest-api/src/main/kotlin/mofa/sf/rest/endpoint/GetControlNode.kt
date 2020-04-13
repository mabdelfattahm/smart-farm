package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.db.DbConnectionPool
import mofa.sf.db.h2.repository.ControllerRepo
import mofa.sf.domain.controller.Controller
import mofa.sf.rest.dto.response.ControllerResponse
import mofa.sf.usecase.controller.ControllerStorage
import java.util.concurrent.CompletableFuture

interface GetControlNode {
    fun list(): CompletableFuture<Collection<Controller>>

    class Default(private val pool: DbConnectionPool) : GetControlNode {
        override fun list(): CompletableFuture<Collection<Controller>> {
            return CoroutineScope(Dispatchers.IO).future {
                ControllerStorage(ControllerRepo(pool.get())).list().map { ControllerResponse(it) }
            }
        }
    }

    class Paged(private val pool: DbConnectionPool, private val page: Int, private val size: Int): GetControlNode {
        init {
            if (page < 1) {
                throw IllegalArgumentException("Page cannot be less than 1")
            }
            if (size < 1) {
                throw IllegalArgumentException("Size cannot be less than 1")
            }
        }

        override fun list(): CompletableFuture<Collection<Controller>> {
            return CoroutineScope(Dispatchers.IO).future {
                ControllerStorage(ControllerRepo(pool.get())).list(page, size).map { ControllerResponse(it) }
            }
        }
    }
}