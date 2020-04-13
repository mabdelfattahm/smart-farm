package mofa.sf.postgres.entity

import mofa.sf.db.DbRecord
import mofa.sf.domain.signal.SignalId

class SignalIdEntity(private val record: DbRecord) : SignalId {

    override fun asString(): String {
        return this.record.getInt("id").toString()
    }
}