package mofa.sf.rest.dto.request

import com.fasterxml.jackson.databind.JsonNode
import mofa.sf.domain.controller.Controller
import mofa.sf.domain.controller.ControllerId
import mofa.sf.domain.controller.ControllerName
import mofa.sf.domain.controller.ControllerStatus
import mofa.sf.domain.farm.FarmId
import mofa.sf.domain.geofeature.GeoLocation

class ControllerRequest(private val json: JsonNode): Controller {

    override fun id(): ControllerId {
        throw IllegalStateException("No Id for request DTO!")
    }

    override fun name(): ControllerName {
        return ControllerName.Default(this.json.get("name").asText())
    }

    override fun location(): GeoLocation {
        return GeoLocation.WktLocation(this.json.get("location").asText())
    }

    override fun farmId(): FarmId {
        return FarmId.Default(this.json.get("farm").asInt())
    }

    override fun status(): ControllerStatus {
        return ControllerStatus.valueOf(this.json.get("status").asText())
    }
}