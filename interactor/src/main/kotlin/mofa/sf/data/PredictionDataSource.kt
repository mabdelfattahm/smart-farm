package mofa.sf.data

import mofa.sf.domain.ml.Prediction

interface PredictionDataSource {
    suspend fun add(prediction: Prediction): Int
}