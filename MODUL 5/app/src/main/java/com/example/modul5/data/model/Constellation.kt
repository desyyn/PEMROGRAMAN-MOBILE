package com.example.modul5.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Constellation(
    val name: String,
    val description: String,
    val year: String,
    @SerialName("image_url") val imageUrl: String,
    @SerialName("web_url") val webUrl: String
)
