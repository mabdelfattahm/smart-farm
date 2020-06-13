package mofa.sf.domain.controller

/**
 * Controller node id abstraction.
 * @author m_afatah
 * @since 1.0
 */
interface ControllerId {
    /**
     * ID as a string.
     * @author m_afatah
     * @since 1.0
     */
    fun asString(): String

    /**
     * Default implementation for controller Id.
     * @constructor Constructor.
     * @property value Integer id.
     * @author m_afatah
     * @since 1.0
     */
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