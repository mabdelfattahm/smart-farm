package mofa.sf.domain.reading

interface Moisture {
    fun asDouble(): Double

    class Default(private val value: Double) : Moisture {
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