package mofa.sf.rest.endpoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import mofa.sf.db.DbConnectionPool
import mofa.sf.db.h2.repository.PredictionRepo
import mofa.sf.domain.ml.Prediction
import mofa.sf.usecase.ml.PredictionStorage
import java.util.concurrent.CompletableFuture

interface AddPrediction {
    fun add(prediction: Prediction): CompletableFuture<Int>

    class Default(private val pool: DbConnectionPool) : AddPrediction {
        override fun add(prediction: Prediction): CompletableFuture<Int> {
            return CoroutineScope(Dispatchers.IO).future {
                PredictionStorage(PredictionRepo(pool.get())).add(prediction)
            }
        }
    }
}