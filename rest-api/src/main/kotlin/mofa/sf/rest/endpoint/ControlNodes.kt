package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.postgres.config.DbConnectionProvider
import mofa.sf.domain.controller.Controller
import mofa.sf.postgres.repository.ControllerRepo
import mofa.sf.rest.dto.ControllerDto
import mofa.sf.usecase.controller.ControllerStorage
import java.util.concurrent.CompletableFuture

interface ControlNodes {
    fun list(): CompletableFuture<Collection<Controller>>

    class Default(private val provider: DbConnectionProvider) : ControlNodes {
        override fun list(): CompletableFuture<Collection<Controller>> {
            return CoroutineScope(Dispatchers.IO).future {
                ControllerStorage(ControllerRepo(provider)).list().map { ControllerDto(it) }
            }
        }
    }

    class Paged(private val provider: DbConnectionProvider, private val page: Int, private val size: Int): ControlNodes {
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
                ControllerStorage(ControllerRepo(provider)).list(page, size).map { ControllerDto(it) }
            }
        }
    }
}