package com.example.modul5.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ConstellationDao {
    @Query("SELECT * FROM constellations")
    fun getAll(): Flow<List<ConstellationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<ConstellationEntity>)
}
