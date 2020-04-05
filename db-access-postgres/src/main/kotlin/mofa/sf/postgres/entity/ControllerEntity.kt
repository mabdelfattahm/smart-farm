package mofa.sf.postgres.entity

import mofa.sf.db.DbRecord
import mofa.sf.domain.controller.Controller
import mofa.sf.domain.controller.ControllerId
import mofa.sf.domain.controller.ControllerName
import mofa.sf.domain.controller.ControllerStatus
import mofa.sf.domain.farm.FarmId
import mofa.sf.domain.geofeature.GeoLocation

class ControllerEntity(private val data: DbRecord): Controller {
    override fun id(): ControllerId {
        return ControllerId.Default(this.data.getInt("id"))
    }

    override fun name(): ControllerName {
        return ControllerName.Default(this.data.getString("name"))
    }

    override fun location(): GeoLocation {
        return GeoLocation.WktLocation(this.data.getString("location"))
    }

    override fun farmId(): FarmId {
        return FarmId.Default(this.data.getInt("farm"))
    }

    override fun status(): ControllerStatus {
        return ControllerStatus.values().first { it.ordinal == this.data.getInt("status") }
    }
}
