package mofa.sf.rest.dto.request

import com.fasterxml.jackson.databind.JsonNode
import mofa.sf.domain.controller.ControllerId
import mofa.sf.domain.signal.Control
import mofa.sf.domain.signal.Signal
import mofa.sf.domain.signal.SignalId
import mofa.sf.domain.signal.Timestamp

class SignalRequest(private val json: JsonNode): Signal {
    override fun id(): SignalId {
        throw IllegalStateException("No Id for request DTO!")
    }

    override fun controllerId(): ControllerId {
        return ControllerId.Default(this.json.get("controller").asInt())
    }

    override fun timestamp(): Timestamp {
        return Timestamp.Default(this.json.get("timestamp").asLong())
    }

    override fun controlValue(): Control {
        return Control.Default(this.json.get("signal").asDouble())
    }
}