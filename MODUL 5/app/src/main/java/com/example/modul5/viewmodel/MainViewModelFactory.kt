package com.example.modul5.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modul5.data.local.ConstellationDatabase
import com.example.modul5.data.repository.ConstellationRepository

class MainViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val dao = ConstellationDatabase.getInstance(context).constellationDao()
            val repo = ConstellationRepository(dao)
            return MainViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
