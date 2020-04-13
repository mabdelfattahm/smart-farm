package mofa.sf.rest.dto.response

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializable
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.jsontype.TypeSerializer
import mofa.sf.domain.farm.FarmId
import mofa.sf.domain.geofeature.GeoLocation
import mofa.sf.domain.sensor.Sensor
import mofa.sf.domain.sensor.SensorId
import mofa.sf.domain.sensor.SensorName
import mofa.sf.domain.sensor.SensorStatus

class SensorResponse(private val sensor: Sensor): Sensor, JsonSerializable.Base() {
    override fun id(): SensorId {
        return this.sensor.id()
    }

    override fun name(): SensorName {
        return this.sensor.name()
    }

    override fun location(): GeoLocation {
        return this.sensor.location()
    }

    override fun farmId(): FarmId {
        return this.sensor.farmId()
    }

    override fun status(): SensorStatus {
        return this.sensor.status()
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