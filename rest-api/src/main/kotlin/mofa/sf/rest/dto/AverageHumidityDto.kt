package mofa.sf.rest.dto

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializable
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.jsontype.TypeSerializer
import mofa.sf.domain.reading.Humidity
import mofa.sf.domain.reading.Temperature
import mofa.sf.domain.reading.Timestamp

class AverageHumidityDto(private val pair: Pair<Timestamp, Humidity>): JsonSerializable{
    override fun serializeWithType(generator: JsonGenerator, provider: SerializerProvider?, type: TypeSerializer?) {
        serialize(generator, provider)
    }

    override fun serialize(generator: JsonGenerator, provider: SerializerProvider?) {
        generator.writeStartObject()
        generator.writeNumberField("timestamp", this.pair.first.asLong())
        generator.writeNumberField("humidity", this.pair.second.asDouble())
        generator.writeEndObject()
    }
}