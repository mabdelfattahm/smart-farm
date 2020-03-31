package mofa.sf.domain.farm

import mofa.sf.domain.geofeature.GeoLocation

interface Farm {
    fun id(): FarmId
    fun farmName(): FarmName
    fun location(): GeoLocation
}