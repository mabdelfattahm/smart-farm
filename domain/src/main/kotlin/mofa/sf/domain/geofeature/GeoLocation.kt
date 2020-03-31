package mofa.sf.domain.geofeature

interface GeoLocation {

    fun asString(): String

    class WktLocation(private val value: String): GeoLocation {
        override fun asString(): String {
            return this.value
        }
    }
}