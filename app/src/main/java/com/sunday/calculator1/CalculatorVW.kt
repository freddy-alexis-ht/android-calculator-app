package com.sunday.calculator1

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorVW : ViewModel() {

    var calculation by mutableStateOf("0")
        private set
    var result by mutableStateOf("=")
        private set
    var darkMode by mutableStateOf(false)
        private set

    private val oneToNine = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
    private val operations = arrayListOf("+", "-", "*", "/")
    private val zeroToNine = oneToNine + "0"
    private val operationsAndDot = operations + "."
    private val operationsAndDotAndEquals = operationsAndDot + "="
    private val operationsAndDotAndOneToNine = operationsAndDot + oneToNine
    private val operationsAndDotAndZeroToNine = operationsAndDot + zeroToNine

    fun onEvent(event: CalculatorEvent) {
        val firstEntered = calculation.first().toString()
        val lastEntered = calculation.last().toString()
        val previousLastEntered =
            if (calculation.length > 1) calculation.dropLast(1).last().toString() else 0

        when (event) {

            is CalculatorEvent.OnOneToNine -> {
                if (calculation == "0") {
                    calculation = event.number
                } else if (lastEntered in operationsAndDotAndZeroToNine) {
                    calculation += event.number
                } else {
                    Log.i("MyTag", "NumPressed .. no hará nada")
                }
            }

            CalculatorEvent.OnZero -> {
                if (calculation == "0") {
                    Log.i("MyTag", "ZeroPressed .. no hará nada")
                } else if (lastEntered in operationsAndDotAndZeroToNine) {
                    calculation += "0"
                }
            }
            CalculatorEvent.OnDot -> {
                if (lastEntered in zeroToNine && !calculation.contains(".")) {
                    calculation += "."
                } else if (lastEntered in operationsAndDotAndEquals) {
                    Log.i("MyTag", "DotPressed .. no hará nada")
                }
                // Si ya hay un . y hay un operador ->
                else if (calculation.contains(".") && operations.filter { calculation.contains(it) }
                        .isNotEmpty()) {
                    if (calculation.indexOf(".") < calculation.indexOfAny(operations)) {
                        calculation += "."
                    } else if (calculation.indexOf(".") > calculation.indexOfAny(operations) &&
                        calculation.indexOf(".") < calculation.indexOfAny(operations, 1)
                    ) {
                        calculation += "."
                    }
                }
            }
            is CalculatorEvent.OnOperation -> {
                if (lastEntered == "." && event.op == "-") {
                    Log.i("MyTag", "OpPressed .. no hará nada 1")
                } else if (calculation == "0" && event.op == "-") {
                    calculation = event.op
                } else if (calculation.length >= 3 && calculation.indexOfAny(operations, 1) != -1) {
                    var operator = calculation.indexOfAny(operations, 1) + 1
                    var length = calculation.length
                    if (operator == length && event.op == "-")
                        calculation += event.op
                    if (operator < length && lastEntered == "-")
                        Log.i("MyTag", "OpPressed .. no hará nada 2")
                    if (operator < length && lastEntered in zeroToNine)
                        Log.i("MyTag", "OpPressed .. no hará nada 3")
                }
                else if (lastEntered in zeroToNine) {
                    calculation += event.op
                }
                else if (lastEntered in operationsAndDot && calculation != "-") {
                    if (event.op == "-" && previousLastEntered !in operationsAndDot)
                        calculation += event.op
                    Log.i("MyTag", "OpPressed .. no hará nada 4")
                }
            }
            CalculatorEvent.OnClear -> {
                calculation = "0"
                result = "="
            }
            CalculatorEvent.OnDelete -> {
                if (calculation == "0") {
                    Log.i("MyTag", "DeletePressed .. no hará nada")
                } else if (lastEntered in oneToNine && calculation.length == 1) {
                    calculation = "0"
                } else if (lastEntered in operationsAndDotAndOneToNine) {
                    calculation = calculation.dropLast(1)
                }
            }
            CalculatorEvent.OnEquals -> {
                if (lastEntered in operationsAndDotAndEquals) {
                    Log.i("MyTag", "EqualsPressed .. no hará nada 1")
                }
                // Si el número no contiene op -> no hará nada
                else if (operations.filter { calculation.contains(it) }.isEmpty()) {
                    Log.i("MyTag", "EqualsPressed .. no hará nada 2")
                }
                // Si el número contiene un op, pero es el primero y es '-' -> no hará nada
                else if (calculation.indexOfAny(operations, 1) == -1 && firstEntered == "-") {
                    Log.i("MyTag", "EqualsPressed .. no hará nada 3")
                }
                // Si contiene operador (sin considerar si el primero es '-') -> hará la operación
                else {
                    var operationIndex = calculation.indexOfAny(operations)
                    operationIndex = if (operationIndex == 0) {
                        (calculation.indexOfAny(operations, 1))
                    } else operationIndex
                    var operator = calculation.get(operationIndex).toString()
                    var arg1 = calculation.subSequence(0, operationIndex).toString().toFloat()
                    var arg2 =
                        calculation.subSequence(operationIndex + 1, calculation.length).toString()
                            .toFloat()

                    when (operator) {
                        "+" -> {
                            var temp = (arg1 + arg2)
                            if (hasSevenDecimals(temp)) temp = withoutImprecision(temp)
                            result = rounding(temp)
                        }
                        "-" -> {
                            var temp = (arg1 - arg2)
                            if (hasSevenDecimals(temp)) temp = withoutImprecision(temp)
                            result = rounding(temp)
                        }
                        "*" -> {
                            var temp = (arg1 * arg2)
                            if (hasSevenDecimals(temp)) temp = withoutImprecision(temp)
                            result = rounding(temp)
                        }
                        "/" -> {
                            result = if (arg2 == 0.0f) "NaN"
                            else {
                                var temp = arg1 / arg2
                                if (hasSevenDecimals(temp)) temp = withoutImprecision(temp)
                                rounding(temp)
                            }
                        }
                    }
                    calculation += "="
                }
            }
            CalculatorEvent.OnMode -> {
                darkMode = !darkMode
            }
        }
    }

    private fun hasSevenDecimals(f: Float): Boolean {
        val n = f.toString()
        val dotIndex = n.indexOf(".")
        val onlyDecimals = n.subSequence(dotIndex + 1, n.length)
        return onlyDecimals.length == 7
    }

    private fun withoutImprecision(f: Float): Float {
        val n = f.toString()
        var refactor = n.dropLast(1)
        while (refactor.last().toString() == "0") {
            refactor = refactor.dropLast(1)
        }
        return refactor.toFloat()
    }

    private fun rounding(temp: Float): String {
        return if (temp.rem(temp.toInt()) != 0.0f) temp.toString()
        else temp.toInt().toString()
    }
}