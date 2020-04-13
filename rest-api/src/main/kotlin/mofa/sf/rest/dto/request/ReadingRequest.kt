package mofa.sf.rest.dto.request

import com.fasterxml.jackson.databind.JsonNode
import mofa.sf.domain.reading.*
import mofa.sf.domain.sensor.SensorId

class ReadingRequest(private val json: JsonNode): Reading {

    override fun id(): ReadingId {
        throw IllegalStateException("No Id for request DTO!")
    }

    override fun sensorId(): SensorId {
        return SensorId.Default(this.json.get("sensor").asInt())
    }

    override fun timestamp(): Timestamp {
        return Timestamp.Default(this.json.get("timestamp").asLong())
    }

    override fun temperature(): Temperature {
        return Temperature.Default(this.json.get("temperature").asDouble())
    }

    override fun humidity(): Humidity {
        return Humidity.Default(this.json.get("humidity").asDouble())
    }

    override fun moisture(): Moisture {
        return Moisture.Default(this.json.get("moisture").asDouble())
    }

}