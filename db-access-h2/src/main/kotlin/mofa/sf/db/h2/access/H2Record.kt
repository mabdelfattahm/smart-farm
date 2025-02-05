package mofa.sf.db.h2.access

import io.r2dbc.spi.Row
import io.r2dbc.spi.RowMetadata
import mofa.sf.db.DbRecord
import org.locationtech.jts.geom.Geometry
import java.time.LocalDateTime

class H2Record(private val row: Row, private val metadata: RowMetadata) : DbRecord {
    override fun getInt(column: String): Int {
        return this.row.get(column, (metadata.getColumnMetadata(column).javaType as Class<*>)) as Int
    }

    override fun getDouble(column: String): Double {
        return this.row.get(column, (metadata.getColumnMetadata(column).javaType as Class<*>)) as Double
    }

    override fun getString(column: String): String {
        return this.row.get(column, (metadata.getColumnMetadata(column).javaType as Class<*>)) as String
    }

    override fun getLocalDate(column: String): LocalDateTime {
        return this.row.get(column, LocalDateTime::class.java)!!
    }

    override fun getLocationAsWktString(column: String): String {
        return this.row.get(column, Geometry::class.java)?.toText() ?: "POINT (0 0)"
    }
}