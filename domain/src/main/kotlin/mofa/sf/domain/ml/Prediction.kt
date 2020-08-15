package mofa.sf.domain.ml

data class Prediction(
    val rmse: Double,
    val rrmse: Double,
    val me: Double,
    val rme: Double,
    val cls: PredictionClass
)