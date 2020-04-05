package mofa.sf.postgres.entity

import mofa.sf.db.DbRecord
import mofa.sf.domain.farm.Farm
import mofa.sf.domain.farm.FarmId
import mofa.sf.domain.farm.FarmName
import mofa.sf.domain.geofeature.GeoLocation

class FarmEntity(private val data: DbRecord) : Farm {
    override fun id(): FarmId {
        return FarmId.Default(this.data.getInt("id"))
    }

    override fun farmName(): FarmName {
        return FarmName.Default(this.data.getString("name"))
    }

    override fun location(): GeoLocation {
        return GeoLocation.WktLocation("")
    }
}