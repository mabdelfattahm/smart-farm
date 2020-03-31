package mofa.sf.domain.signal

interface SignalId {
    fun asString(): String

    class Default(private val value: Int) : SignalId {
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