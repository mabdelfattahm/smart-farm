package mofa.sf.domain.sensor

enum class SensorStatus {
    ON, OFF;

    fun asString(): String {
        return when(this) {
            ON -> "ON"
            else -> "OFF"
        }
    }
}