package mofa.sf.h2.entity

import io.r2dbc.spi.Row
import mofa.sf.domain.farm.Farm
import mofa.sf.domain.farm.FarmId
import mofa.sf.domain.farm.FarmName
import mofa.sf.domain.geofeature.GeoLocation

class FarmEntity(private val data: Row) : Farm {
    override fun id(): FarmId {
        return FarmId.Default(this.data.get("id", Int::class.java)!!)
    }

    override fun farmName(): FarmName {
        return FarmName.Default(this.data.get("name", String::class.java)!!)
    }

    override fun location(): GeoLocation {
        return GeoLocation.WktLocation("")
    }
}