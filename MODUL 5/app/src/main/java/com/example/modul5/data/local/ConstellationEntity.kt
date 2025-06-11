package com.example.modul5.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "constellations")
data class ConstellationEntity(
    @PrimaryKey val name: String,
    val description: String,
    val year: String,
    val imageUrl: String,
    val webUrl: String
) : Parcelable
