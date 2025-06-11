package com.example.modul5.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ConstellationEntity::class], version = 1, exportSchema = false)
abstract class ConstellationDatabase : RoomDatabase() {
    abstract fun constellationDao(): ConstellationDao

    companion object {
        @Volatile
        private var INSTANCE: ConstellationDatabase? = null

        fun getInstance(context: Context): ConstellationDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ConstellationDatabase::class.java,
                    "constellation_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
