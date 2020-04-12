package mofa.sf.db.h2.entity

import mofa.sf.db.DbRecord
import mofa.sf.domain.farm.Farm
import mofa.sf.domain.farm.FarmId
import mofa.sf.domain.farm.FarmName
import mofa.sf.domain.geofeature.GeoLocation

class FarmEntity(private val record: DbRecord) : Farm {
    override fun id(): FarmId {
        return FarmId.Default(this.record.getInt("id"))
    }

    override fun farmName(): FarmName {
        return FarmName.Default(this.record.getString("name"))
    }

    override fun location(): GeoLocation {
        return GeoLocation.WktLocation(this.record.getLocationAsWktString("location"))
    }
}