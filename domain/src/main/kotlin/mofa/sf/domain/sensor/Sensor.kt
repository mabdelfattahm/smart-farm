package mofa.sf.domain.sensor

import mofa.sf.domain.farm.FarmId
import mofa.sf.domain.geofeature.GeoLocation

interface Sensor{
    fun id(): SensorId
    fun name(): SensorName
    fun location(): GeoLocation
    fun farmId(): FarmId
    fun status(): SensorStatus
}