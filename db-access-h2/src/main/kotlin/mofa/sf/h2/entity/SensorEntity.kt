package mofa.sf.h2.entity

import io.r2dbc.spi.Row
import mofa.sf.domain.farm.FarmId
import mofa.sf.domain.geofeature.GeoLocation
import mofa.sf.domain.sensor.Sensor
import mofa.sf.domain.sensor.SensorId
import mofa.sf.domain.sensor.SensorName
import mofa.sf.domain.sensor.SensorStatus

class SensorEntity(private val data: Row) : Sensor {
    override fun id(): SensorId {
        return SensorId.Default(this.data.get("id", Int::class.java)!!)
    }

    override fun name(): SensorName {
        return SensorName.Default(this.data.get("name", String::class.java)!!)
    }

    override fun location(): GeoLocation {
        return GeoLocation.WktLocation(this.data.get("location", String::class.java)!!)
    }

    override fun farmId(): FarmId {
        return FarmId.Default(this.data.get("farm", Int::class.java)!!)
    }

    override fun status(): SensorStatus {
        return SensorStatus.values().first { it.ordinal == this.data.get("status", Int::class.java)!! }
    }
}