package mofa.sf.domain.controller

import mofa.sf.domain.farm.FarmId
import mofa.sf.domain.geofeature.GeoLocation

interface Controller {
    fun id(): ControllerId
    fun name(): ControllerName
    fun location(): GeoLocation
    fun farmId(): FarmId
    fun status(): ControllerStatus
}