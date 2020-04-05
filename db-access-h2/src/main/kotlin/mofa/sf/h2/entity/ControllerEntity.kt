package mofa.sf.h2.entity

import io.r2dbc.spi.Row
import mofa.sf.domain.controller.Controller
import mofa.sf.domain.controller.ControllerId
import mofa.sf.domain.controller.ControllerName
import mofa.sf.domain.controller.ControllerStatus
import mofa.sf.domain.farm.FarmId
import mofa.sf.domain.geofeature.GeoLocation

class ControllerEntity(private val data: Row): Controller {
    override fun id(): ControllerId {
        return ControllerId.Default(this.data.get("id", Int::class.java)!!)
    }

    override fun name(): ControllerName {
        return ControllerName.Default(this.data.get("name", String::class.java)!!)
    }

    override fun location(): GeoLocation {
        return GeoLocation.WktLocation(this.data.get("location", String::class.java)!!)
    }

    override fun farmId(): FarmId {
        return FarmId.Default(this.data.get("farm", Int::class.java)!!)
    }

    override fun status(): ControllerStatus {
        return ControllerStatus.values().first { it.ordinal == this.data.get("status", Int::class.java)!! }
    }
}
