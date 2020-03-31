package mofa.sf.domain.reading

interface ReadingId {
    fun asString(): String

    class Default(private val value: Int) : ReadingId {
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