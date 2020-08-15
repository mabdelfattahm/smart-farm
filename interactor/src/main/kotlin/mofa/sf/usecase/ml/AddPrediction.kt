package mofa.sf.usecase.ml

import mofa.sf.domain.ml.Prediction

interface AddPrediction {
    suspend fun add(prediction: Prediction): Int
}