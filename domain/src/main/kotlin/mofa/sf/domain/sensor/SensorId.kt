package mofa.sf.domain.sensor

interface SensorId {
    fun asString(): String

    class Default(private val value: Int) : SensorId {
        init {
            if (value < 1) {
                throw IllegalArgumentException("Invalid value")
            }
        }

        override fun asString(): String {
            return this.value.toString()
        }
    }
}