package mofa.sf.domain.sensor

interface SensorName {
    fun asString(): String

    class Default(private val value: String) : SensorName {
        init {
            if (value.isBlank()) {
                throw IllegalArgumentException("Invalid value")
            }
        }

        override fun asString(): String {
            return this.value
        }
    }
}
