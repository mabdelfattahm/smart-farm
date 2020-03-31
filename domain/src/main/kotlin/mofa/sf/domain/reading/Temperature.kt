package mofa.sf.domain.reading

interface Temperature {
    fun asDouble(): Double

    class Default(private val value: Double) : Temperature {
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