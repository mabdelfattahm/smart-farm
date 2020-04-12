package mofa.sf.db.h2.entity

import mofa.sf.db.DbRecord
import mofa.sf.domain.farm.FarmId
import mofa.sf.domain.geofeature.GeoLocation
import mofa.sf.domain.sensor.Sensor
import mofa.sf.domain.sensor.SensorId
import mofa.sf.domain.sensor.SensorName
import mofa.sf.domain.sensor.SensorStatus

class SensorEntity(private val record: DbRecord) : Sensor {
    override fun id(): SensorId {
        return SensorId.Default(this.record.getInt("id"))
    }

    override fun name(): SensorName {
        return SensorName.Default(this.record.getString("name"))
    }

    override fun location(): GeoLocation {
        return GeoLocation.WktLocation(this.record.getLocationAsWktString("location"))
    }

    override fun farmId(): FarmId {
        return FarmId.Default(this.record.getInt("farm"))
    }

    override fun status(): SensorStatus {
        return SensorStatus.values().first { it.ordinal == this.record.getInt("status") }
    }
}