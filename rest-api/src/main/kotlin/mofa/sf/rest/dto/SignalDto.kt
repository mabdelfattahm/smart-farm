package mofa.sf.rest.dto

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializable
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.jsontype.TypeSerializer
import mofa.sf.domain.controller.Controller
import mofa.sf.domain.controller.ControllerId
import mofa.sf.domain.controller.ControllerName
import mofa.sf.domain.controller.ControllerStatus
import mofa.sf.domain.farm.FarmId
import mofa.sf.domain.geofeature.GeoLocation
import mofa.sf.domain.signal.Control
import mofa.sf.domain.signal.Signal
import mofa.sf.domain.signal.SignalId
import mofa.sf.domain.signal.Timestamp

class SignalDto(private val signal: Signal): Signal, JsonSerializable {
    override fun id(): SignalId {
        return this.signal.id()
    }

    override fun controllerId(): ControllerId {
        return this.signal.controllerId()
    }

    override fun timestamp(): Timestamp {
        return this.signal.timestamp()
    }

    override fun controlValue(): Control {
        return this.signal.controlValue()
    }

    override fun serializeWithType(generator: JsonGenerator, provider: SerializerProvider?, type: TypeSerializer?) {
        serialize(generator, provider)
    }

    override fun serialize(generator: JsonGenerator, provider: SerializerProvider?) {
        generator.writeStartObject()
        generator.writeStringField("id", this.id().asString())
        generator.writeStringField("controller", this.controllerId().asString())
        generator.writeNumberField("timestamp", this.timestamp().asLong())
        generator.writeNumberField("value", this.controlValue().asDouble())
        generator.writeEndObject()
    }
}