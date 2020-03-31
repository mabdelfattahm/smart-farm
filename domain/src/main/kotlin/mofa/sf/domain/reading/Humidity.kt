package mofa.sf.domain.reading

interface Humidity {
    fun asDouble(): Double

    class Default(private val value: Double) : Humidity {
        init {
            if (value.isNaN()) {
                throw IllegalArgumentException("Invalid value")
            }
        }

        override fun asDouble(): Double {
            return this.value
        }
    }
}