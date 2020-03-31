package mofa.sf.domain.farm

interface FarmName{
    fun asString(): String

    class Default(private val value: String) : FarmName {
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
