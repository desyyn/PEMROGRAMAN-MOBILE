package com.example.modul5.data.repository

import com.example.modul5.data.local.ConstellationDao
import com.example.modul5.data.local.ConstellationEntity
import com.example.modul5.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.emitAll

class ConstellationRepository(
    private val dao: ConstellationDao
) {

    fun getConstellations(): Flow<List<ConstellationEntity>> = flow {
        try {
            val response = RetrofitInstance.api.getConstellations()
            if (response.status == "success") {
                val mapped = response.data.map {
                    ConstellationEntity(
                        name = it.name,
                        description = it.description,
                        year = it.year,
                        imageUrl = it.imageUrl,
                        webUrl = it.webUrl
                    )
                }
                dao.insertAll(mapped)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        emitAll(dao.getAll())
    }
}
