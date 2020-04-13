package mofa.sf.postgres.entity

import mofa.sf.db.DbRecord
import mofa.sf.domain.farm.FarmId

class FarmIdEntity(private val record: DbRecord) : FarmId {
    override fun asString(): String {
        return this.record.getInt("id").toString()
    }
}