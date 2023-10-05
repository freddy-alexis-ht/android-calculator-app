package com.sunday.calculator1

import org.junit.Test
import java.math.BigDecimal
import java.math.RoundingMode

class ExampleUnitTest {
    private val operations = arrayListOf("+", "-", "*", "/")

    @Test
    fun addition_isCorrect() {
        val decimal = BigDecimal(3.14159265359).setScale(2, RoundingMode.HALF_EVEN)
//        println(decimal) // 3.14
        println(2.800010/2) // 3.14
        println(2.800010f/2) // 3.14
//        println(10/3.0) // 3.14
//        println(10/3.0f) // 3.14
    //
//        var n1 = BigDecimal("6.0")
        var n1 = BigDecimal(6.0)
////        println(n1)
//        var n2 = BigDecimal("9.0")
        var n2 = BigDecimal(9.0)
//        var n3 = n1.divide(n2)

//        println(6/9.0)
//        println(BigDecimal(10/3.0).setScale(3, RoundingMode.HALF_EVEN))
//        println(n3.setScale(3, RoundingMode.HALF_EVEN))
//        println(n1.divide(n2, RoundingMode.HALF_UP).toString())

        var n = "32.25"
        var dotIndex = n.indexOf(".")
        var onlyDecimalsX = n.subSequence(0, dotIndex+1)
        var onlyDecimals = n.subSequence(dotIndex+1, n.length)
//        println(onlyDecimalsX)
//        println(onlyDecimals)
    }
}