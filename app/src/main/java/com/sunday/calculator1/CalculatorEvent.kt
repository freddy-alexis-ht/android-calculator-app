package com.sunday.calculator1

sealed class CalculatorEvent {
    data class OnOneToNine(val number:String): CalculatorEvent()
    object OnZero: CalculatorEvent()
    object OnDot: CalculatorEvent()
    data class OnOperation(val op: String): CalculatorEvent()
    object OnEquals: CalculatorEvent()
    object OnClear: CalculatorEvent()
    object OnDelete: CalculatorEvent()
    object OnMode: CalculatorEvent()
}