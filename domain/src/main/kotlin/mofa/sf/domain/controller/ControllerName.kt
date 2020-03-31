package mofa.sf.domain.controller

interface ControllerName {
    fun asString(): String

    class Default(private val value: String) : ControllerName {
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
