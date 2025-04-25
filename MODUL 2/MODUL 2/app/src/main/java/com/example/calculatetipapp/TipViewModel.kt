package com.example.calculatetipapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.ceil

class TipViewModel : ViewModel() {
    var serviceCostInput by mutableStateOf("")
    var selectedTipPercent by mutableStateOf(18)
    var roundUp by mutableStateOf(false)
    var showTip by mutableStateOf(false)

    val calculatedTip: String
        get() {
            val cost = serviceCostInput.toDoubleOrNull() ?: return "0.00"
            var tip = cost * selectedTipPercent / 100
            if (roundUp) tip = ceil(tip)
            return String.format("%.2f", tip)
        }

    fun onCalculateClicked() {
        showTip = true
    }
}