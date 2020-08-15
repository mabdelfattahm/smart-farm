package mofa.sf.rest.dto.request

import com.fasterxml.jackson.databind.JsonNode
import mofa.sf.domain.ml.Prediction
import mofa.sf.domain.ml.PredictionClass

class PredictionRequest(private val node: JsonNode) {
    fun toDomain(): Prediction {
        return Prediction(
            node.get("rmse").doubleValue(),
            node.get("rrmse").doubleValue(),
            node.get("me").doubleValue(),
            node.get("rme").doubleValue(),
            PredictionClass.fromString(node.get("class").textValue())
        )
    }
}