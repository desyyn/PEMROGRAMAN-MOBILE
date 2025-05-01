package com.example.biografimahasiswa

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mahasiswa(
    val nama: String,
    val nim: String,
    val prodi: String,
    val ttl: String,
    val pendidikan: String,
    val minat: String,
    val organisasi: String,
    val tujuan: String,
    val foto: Int
) : Parcelable

