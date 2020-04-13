package mofa.sf.postgres.entity

import mofa.sf.db.DbRecord
import mofa.sf.domain.controller.ControllerId

class ControllerIdEntity(private val record: DbRecord): ControllerId {
    override fun asString(): String {
        return this.record.getInt("id").toString()
    }
}
