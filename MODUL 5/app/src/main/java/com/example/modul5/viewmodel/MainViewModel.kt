package com.example.modul5.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul5.data.local.ConstellationEntity
import com.example.modul5.data.repository.ConstellationRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: ConstellationRepository
) : ViewModel() {

    private val _state = MutableStateFlow<List<ConstellationEntity>>(emptyList())
    val state: StateFlow<List<ConstellationEntity>> = _state.asStateFlow()

    init {
        fetchConstellations()
    }

    private fun fetchConstellations() {
        viewModelScope.launch {
            repository.getConstellations()
                .catch { it.printStackTrace() }
                .collect { _state.value = it }
        }
    }
}
