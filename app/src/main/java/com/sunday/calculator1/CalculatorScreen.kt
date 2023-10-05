package com.sunday.calculator1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorScreen(vm: CalculatorVW) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {
        ThemeIcon(vm)
        Calculator(vm, modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun ThemeIcon(vm: CalculatorVW) {
    Box(contentAlignment = Alignment.TopStart) {
        IconButton(onClick = { vm.onEvent(CalculatorEvent.OnMode) }) {
            if(vm.darkMode)
                Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_light), contentDescription = "light-mode")
            else
                Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_dark), contentDescription = "dark-mode")
        }
    }
}

@Composable
fun Calculator(vm: CalculatorVW, modifier: Modifier) {
    Column(modifier = modifier) {
        TextCalculation(vm)
        TextResult(vm)
        Row1ClearAndDivision(vm)
        Row2SevenAndMultiplication(vm)
        Row3FourAndMinus(vm)
        Row4OneAndPlus(vm)
        Row5ZeroAndEquals(vm)
    }
}

@Composable
fun TextCalculation(vm: CalculatorVW) {
    Text(
        text = vm.calculation,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(50.dp)
            .background(MaterialTheme.colors.secondary),
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.End,
        color = MaterialTheme.colors.onSecondary,
    )
}

@Composable
fun TextResult(vm: CalculatorVW) {
    Text(
        text = vm.result,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(30.dp)
            .background(MaterialTheme.colors.secondary),
        fontSize = 24.sp,
        textAlign = TextAlign.End,
        color = MaterialTheme.colors.onSurface
    )
}

@Composable
fun Row1ClearAndDivision(vm: CalculatorVW) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ButtonClear(vm)
        ButtonDelete(vm)
        ButtonEmpty()
        ButtonOperation(vm, operation = "/")
    }
}

@Composable
fun Row2SevenAndMultiplication(vm: CalculatorVW) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ButtonNumber(vm, number = "7")
        ButtonNumber(vm, number = "8")
        ButtonNumber(vm, number = "9")
        ButtonOperation(vm, operation = "*")
    }
}

@Composable
fun Row3FourAndMinus(vm: CalculatorVW) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ButtonNumber(vm, number = "4")
        ButtonNumber(vm, number = "5")
        ButtonNumber(vm, number = "6")
        ButtonOperation(vm, operation = "-")
    }
}

@Composable
fun Row4OneAndPlus(vm: CalculatorVW) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ButtonNumber(vm, number = "1")
        ButtonNumber(vm, number = "2")
        ButtonNumber(vm, number = "3")
        ButtonOperation(vm, operation = "+")
    }
}

@Composable
fun Row5ZeroAndEquals(vm: CalculatorVW) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ButtonEmpty()
        ButtonNumber(vm, number = "0")
        ButtonDot(vm)
        ButtonEquals(vm)
    }
}

@Composable
fun ButtonNumber(vm: CalculatorVW, number: String) {
    Button(
        onClick = { vm.onEvent(CalculatorEvent.OnOneToNine(number)) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            contentColor = MaterialTheme.colors.onPrimary,
        )
    ) {
        Text(
            text = number,
            modifier = Modifier.height(30.dp),
            fontSize = 20.sp
        )
    }
}

@Composable
fun ButtonOperation(vm: CalculatorVW, operation: String) {
    Button(
        onClick = { vm.onEvent(CalculatorEvent.OnOperation(operation)) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
        )
    ) {
        Text(
            text = operation,
            modifier = Modifier.height(30.dp),
            fontSize = 20.sp,
        )
    }
}

@Composable
fun ButtonEquals(vm: CalculatorVW) {
    Button(
        onClick = { vm.onEvent(CalculatorEvent.OnEquals) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
        )
    ) {
        Text(
            text = "=",
            modifier = Modifier.height(30.dp),
            fontSize = 20.sp,
        )
    }
}

@Composable
fun ButtonClear(vm: CalculatorVW) {
    Button(
        onClick = { vm.onEvent(CalculatorEvent.OnClear) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
        )
    ) {
        Text(
            text = "C",
            modifier = Modifier.height(30.dp),
            fontSize = 20.sp,
        )
    }
}

@Composable
fun ButtonDelete(vm: CalculatorVW) {
    Button(
        onClick = { vm.onEvent(CalculatorEvent.OnDelete) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
        )
    ) {
        Text(
            text = "<-",
            modifier = Modifier.height(30.dp),
            fontSize = 20.sp,
        )
    }
}

@Composable
fun ButtonDot(vm: CalculatorVW) {
    Button(
        onClick = { vm.onEvent(CalculatorEvent.OnDot) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
        )
    ) {
        Text(
            text = ".",
            modifier = Modifier.height(30.dp),
            fontSize = 20.sp
        )
    }
}

@Composable
fun ButtonEmpty() {
    Button(
        onClick = {  },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background,
            disabledBackgroundColor = MaterialTheme.colors.background
        ), enabled = false
    ) {
        Text(
            text = "",
            modifier = Modifier.height(30.dp),
            fontSize = 20.sp
        )
    }
}