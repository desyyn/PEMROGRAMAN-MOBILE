package com.example.modul4

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Constellation(
    val name: String,
    val description: String,
    val year: String,
    val imageResId: Int,
    val webUrl: String
) : Parcelable
