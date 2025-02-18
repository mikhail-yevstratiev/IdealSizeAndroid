package com.example.idealsizesdk

/**
 * Defines various body or clothing sizes.
 */
enum class SizeEnum(val size: String) {
    S("S"),
    M("M"),
    L("L"),
    XL("XL")
}

/**
 * Exception thrown when the BMI value is out of the acceptable range (1-60).
 */
class InvalidBMIError(message: String) : Exception(message)

/**
 * IdealSize provides the recommended size based on the Body Mass Index (BMI).
 */
object IdealSize {
    /**
     * Calculates the Body Mass Index (BMI) based on height and weight.
     *
     * @param height Height in centimeters.
     * @param weight Weight in kilograms.
     * @return Calculated BMI.
     */
    fun calculateBMI(height: Float, weight: Float): Float {
        val heightInMeters = height / 100
        return weight / (heightInMeters * heightInMeters)
    }

    /**
     * Determines the recommended size based on the Body Mass Index (BMI).
     *
     * @param bmi Body Mass Index.
     * @throws InvalidBMIError if the BMI value is out of the acceptable range (1-60).
     * @return Recommended size of type [SizeEnum].
     */
    @Throws(InvalidBMIError::class)
    fun sizeByBMI(bmi: Float): SizeEnum {
        if (bmi < 1 || bmi >= 60) {
            throw InvalidBMIError("BMI value is out of the acceptable range")
        }

        return when {
            bmi < 18.5 -> SizeEnum.S
            bmi < 25 -> SizeEnum.M
            bmi < 30 -> SizeEnum.L
            else -> SizeEnum.XL
        }
    }
}