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

class ControllerDto(private val controller: Controller): Controller, JsonSerializable {
    override fun id(): ControllerId {
        return this.controller.id()
    }

    override fun name(): ControllerName {
        return this.controller.name()
    }

    override fun location(): GeoLocation {
        return this.controller.location()
    }

    override fun farmId(): FarmId {
        return this.controller.farmId()
    }

    override fun status(): ControllerStatus {
        return this.controller.status()
    }

    override fun serializeWithType(generator: JsonGenerator, provider: SerializerProvider?, type: TypeSerializer?) {
        serialize(generator, provider)
    }

    override fun serialize(generator: JsonGenerator, provider: SerializerProvider?) {
        generator.writeStartObject()
        generator.writeStringField("id", this.id().asString())
        generator.writeStringField("name", this.name().asString())
        generator.writeStringField("location", this.location().asString())
        generator.writeStringField("farm", this.farmId().asString())
        generator.writeStringField("status", this.status().name)
        generator.writeEndObject()
    }
}