package mofa.sf.domain.signal

interface Control {
    fun asDouble(): Double

    class Default(private val value: Double) : Control {

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