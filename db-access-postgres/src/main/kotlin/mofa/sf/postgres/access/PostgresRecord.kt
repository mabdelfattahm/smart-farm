package mofa.sf.postgres.access

import com.github.jasync.sql.db.RowData
import mofa.sf.db.DbRecord
import org.joda.time.DateTimeZone
import java.time.LocalDateTime
import java.time.ZoneOffset

class PostgresRecord(private val row: RowData) : DbRecord {
    override fun getInt(column: String): Int {
        return this.row.getInt(column)!!
    }

    override fun getDouble(column: String): Double {
        return this.row.getDouble(column)!!
    }

    override fun getString(column: String): String {
        return this.row.getString(column)!!
    }

    override fun getLocalDate(column: String): LocalDateTime {
        return LocalDateTime.ofEpochSecond(
            this.row.getDate(column)!!.toDateTime(DateTimeZone.UTC).toInstant().millis,
                0,
                ZoneOffset.UTC
        )
    }

    override fun getLocationAsWktString(column: String): String {
        return this.row.getString(column)!!
    }


}