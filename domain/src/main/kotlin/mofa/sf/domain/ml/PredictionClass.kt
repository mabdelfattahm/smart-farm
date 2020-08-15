package mofa.sf.domain.ml

import java.util.*

enum class PredictionClass {
    COTTON_LEAF, POTATO, NONE;
    companion object {
        fun fromString(str: String): PredictionClass {
            return when (str.toLowerCase(Locale.US)) {
                "cotton_leaf" -> COTTON_LEAF
                "potato" -> POTATO
                else -> NONE
            }
        }
    }
}