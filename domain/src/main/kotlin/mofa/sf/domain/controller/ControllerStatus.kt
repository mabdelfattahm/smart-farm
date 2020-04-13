package mofa.sf.domain.controller

enum class ControllerStatus {
    ON, OFF;

    fun asString(): String {
        return when(this) {
            ON -> "ON"
            else -> "OFF"
        }
    }
}