package mofa.sf.domain.controller

interface ControllerId {
    fun asString(): String

    class Default(private val value: Int) : ControllerId {
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