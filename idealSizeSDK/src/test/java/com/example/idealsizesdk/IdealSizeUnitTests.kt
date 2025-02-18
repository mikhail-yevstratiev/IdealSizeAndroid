package com.example.idealsizesdk

import org.junit.Test
import org.junit.Assert.*

data class SizeWithBMI(val bmi: Float, val size: SizeEnum)

class IdealSizeUnitTests {

    @Test
    fun negativeBMI_returnsError() {
        assertThrows(InvalidBMIError::class.java) {
            IdealSize.sizeByBMI(-1f)
        }
    }

    @Test
    fun zeroBMI_returnsError() {
        assertThrows(InvalidBMIError::class.java) {
            IdealSize.sizeByBMI(0f)
        }
    }

    @Test
    fun bigBMI_returnsError() {
        assertThrows(InvalidBMIError::class.java) {
            IdealSize.sizeByBMI(70f)
        }
    }

    @Test
    fun normalBMI_returnsCorrect() {
        val testCases = listOf(
            SizeWithBMI(bmi = 10f, size = SizeEnum.S),
            SizeWithBMI(bmi = 18.4f, size = SizeEnum.S),
            SizeWithBMI(bmi = 18.5f, size = SizeEnum.M),
            SizeWithBMI(bmi = 24.9f, size = SizeEnum.M),
            SizeWithBMI(bmi = 25f, size = SizeEnum.L),
            SizeWithBMI(bmi = 29.9f, size = SizeEnum.L),
            SizeWithBMI(bmi = 30f, size = SizeEnum.XL),
            SizeWithBMI(bmi = 50f, size = SizeEnum.XL),
        )

        for (case in testCases) {
            val size = IdealSize.sizeByBMI(case.bmi)
            assertEquals("Expected size ${case.size} for BMI ${case.bmi}", case.size, size)
        }
    }
}