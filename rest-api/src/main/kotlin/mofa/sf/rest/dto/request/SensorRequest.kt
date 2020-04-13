package mofa.sf.rest.dto.request

import com.fasterxml.jackson.databind.JsonNode
import mofa.sf.domain.farm.FarmId
import mofa.sf.domain.geofeature.GeoLocation
import mofa.sf.domain.sensor.Sensor
import mofa.sf.domain.sensor.SensorId
import mofa.sf.domain.sensor.SensorName
import mofa.sf.domain.sensor.SensorStatus

class SensorRequest(private val json: JsonNode): Sensor {
    override fun id(): SensorId {
        throw IllegalStateException("No Id for request DTO!")
    }

    override fun name(): SensorName {
        return SensorName.Default(this.json.get("name").asText())
    }

    override fun location(): GeoLocation {
        return GeoLocation.WktLocation(this.json.get("location").asText())
    }

    override fun farmId(): FarmId {
        return FarmId.Default(this.json.get("farm").asInt())
    }

    override fun status(): SensorStatus {
        return SensorStatus.valueOf(this.json.get("status").asText())
    }
}