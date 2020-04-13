package mofa.sf.postgres.entity

import mofa.sf.db.DbRecord
import mofa.sf.domain.reading.ReadingId

class ReadingIdEntity(private val record: DbRecord): ReadingId {

    override fun asString(): String {
        return this.record.getInt("id").toString()
    }
}