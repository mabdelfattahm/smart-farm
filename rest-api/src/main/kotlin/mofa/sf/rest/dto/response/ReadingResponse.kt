package mofa.sf.rest.dto.response

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializable
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.jsontype.TypeSerializer
import mofa.sf.domain.reading.*
import mofa.sf.domain.sensor.SensorId

class ReadingResponse(private val reading: Reading): Reading, JsonSerializable.Base() {

    override fun id(): ReadingId {
        return this.reading.id()
    }

    override fun sensorId(): SensorId {
        return this.reading.sensorId()
    }

    override fun timestamp(): Timestamp {
        return this.reading.timestamp()
    }

    override fun temperature(): Temperature {
        return this.reading.temperature()
    }

    override fun humidity(): Humidity {
        return this.reading.humidity()
    }

    override fun moisture(): Moisture {
        return this.reading.moisture()
    }

    override fun serializeWithType(generator: JsonGenerator, provider: SerializerProvider?, type: TypeSerializer?) {
        serialize(generator, provider)
    }

    override fun serialize(generator: JsonGenerator, provider: SerializerProvider?) {
        generator.writeStartObject()
        generator.writeStringField("id", this.id().asString())
        generator.writeStringField("sensor", this.sensorId().asString())
        generator.writeNumberField("temperature", this.temperature().asDouble())
        generator.writeNumberField("humidity", this.humidity().asDouble())
        generator.writeNumberField("moisture", this.moisture().asDouble())
        generator.writeEndObject()
    }


}