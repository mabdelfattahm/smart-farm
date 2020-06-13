package mofa.sf.domain.controller

/**
 * Controller name abstraction.
 * @author m_afatah
 * @since 1.0
 */
interface ControllerName {
    /**
     * Controller name as a string.
     * @author m_afatah
     * @since 1.0
     */
    fun asString(): String

    /**
     * Default implementation for controller name.
     * @constructor Constructor.
     * @property value Name string.
     * @author m_afatah
     * @since 1.0
     */
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
