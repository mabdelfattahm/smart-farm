package mofa.sf.postgres.entity

import mofa.sf.db.DbRecord
import mofa.sf.domain.sensor.SensorId

class SensorIdEntity(private val record: DbRecord) : SensorId {
    override fun asString(): String {
        return this.record.getInt("id").toString()
    }
}