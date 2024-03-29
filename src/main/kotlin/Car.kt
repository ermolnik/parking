package ru.ermolnik

internal data class Car(val model: Model, val color: Color, val number: Number, val owner: Owner) {
    override fun toString(): String {
        return "${color.colorName} ${model.manufacturer} ${model.model}"
    }
}

internal enum class Color(val colorName: String) {
    RED("red"),
    BLACK("black"),
    WHITE("white"),
    YELLOW("yellow"),
    PURPLE("purple"),
    GRAY("gray"),
    UNKNOWN("unknown");

    companion object {
        fun from(type: String?): Color? = entries.find { it.colorName == type }
    }
}


internal data class Owner(val firstName: String, val lastName: String)

internal data class Number(val number: String, val serial: String, val region: String) {
    companion object {
        fun isValidNumber(number: String, serial: String, region: String): Boolean =
            isNumberValid(number) && isSerialValid(serial) && isRegionValid(region)

        private fun isNumberValid(number: String) = if (number.length !in 3..4) {
            false
        } else if (!number.contains(Constants.digitRegex)) {
            false
        } else true

        private fun isSerialValid(serial: String) = if (serial.length !in 2..3) {
            false
        } else if (!serial.contains(Constants.lettersRegex)) {
            false
        } else true

        private fun isRegionValid(region: String) = if (region.length !in 2..3) {
            false
        } else if (!region.contains(Constants.digitRegex)) {
            false
        } else true
    }
}

internal data class Model(val manufacturer: String, val model: String)