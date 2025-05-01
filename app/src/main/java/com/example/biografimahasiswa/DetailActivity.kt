package com.example.biografimahasiswa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.biografimahasiswa.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mahasiswa = intent.getParcelableExtra<Mahasiswa>("mahasiswa")

        if (mahasiswa != null) {
            displayMahasiswaDetails(mahasiswa)
        }
    }

    private fun displayMahasiswaDetails(mahasiswa: Mahasiswa) {
        binding.tvNamaDetail.text = mahasiswa.nama
        binding.tvNimDetail.text = mahasiswa.nim
        binding.tvProdiDetail.text = mahasiswa.prodi
        binding.tvTtl.text = mahasiswa.ttl
        binding.tvPendidikan.text = mahasiswa.pendidikan
        binding.tvMinat.text = mahasiswa.minat
        binding.tvOrganisasi.text = mahasiswa.organisasi
        binding.tvTujuan.text = mahasiswa.tujuan
        binding.ivFoto.setImageResource(mahasiswa.foto)
    }
}
