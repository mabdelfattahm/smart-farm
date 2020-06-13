package mofa.sf.domain.controller

/**
 * Controller node statuses enum.
 * @author m_afatah
 * @since 1.0
 */
enum class ControllerStatus {
    ON, OFF;

    /**
     * Controller status as string.
     * @author m_afatah
     * @since 1.0
     */
    fun asString(): String {
        return when(this) {
            ON -> "ON"
            else -> "OFF"
        }
    }
}