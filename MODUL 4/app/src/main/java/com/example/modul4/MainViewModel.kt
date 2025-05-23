package com.example.modul4

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val _constellations = MutableStateFlow<List<Constellation>>(emptyList())
    val constellations: StateFlow<List<Constellation>> = _constellations

    private val _selectedItem = MutableStateFlow<Constellation?>(null)
    val selectedItem: StateFlow<Constellation?> = _selectedItem

    init {
        val list = ConstellationData.listConstellations
        _constellations.value = list
        Log.d("ViewModel", "Data item masuk ke dalam list: $list")
    }

    fun onDetailClicked(constellation: Constellation) {
        _selectedItem.value = constellation
        Log.d("ViewModel", "Tombol Detail ditekan. Item: $constellation")
    }

    fun onWebClicked(constellation: Constellation) {
        Log.d("ViewModel", "Tombol Explicit Intent ditekan. URL: ${constellation.webUrl}")
    }
}
