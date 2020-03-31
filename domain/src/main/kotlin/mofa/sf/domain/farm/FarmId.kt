package mofa.sf.domain.farm

interface FarmId {
    fun asString(): String

    class Default(private val value: Int) : FarmId {
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
