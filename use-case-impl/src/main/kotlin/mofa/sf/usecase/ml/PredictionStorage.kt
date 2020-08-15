package mofa.sf.usecase.ml

import mofa.sf.data.PredictionDataSource
import mofa.sf.domain.ml.Prediction

class PredictionStorage(private val ds: PredictionDataSource): AddPrediction {
    override suspend fun add(prediction: Prediction): Int {
        return this.ds.add(prediction)
    }
}