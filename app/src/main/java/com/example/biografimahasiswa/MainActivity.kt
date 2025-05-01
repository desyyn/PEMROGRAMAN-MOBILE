package com.example.biografimahasiswa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.biografimahasiswa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvHeader.text = "Biografi Mahasiswa"
        binding.tvNama.text = "Dessy Nurulita"
        binding.tvNim.text = "231081720024"
        binding.tvProdi.text = "Teknologi Informasi"

        binding.cardMahasiswa.setOnClickListener {
            val mahasiswa = createMahasiswa()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("mahasiswa", mahasiswa)
            startActivity(intent)
        }
    }

    private fun createMahasiswa(): Mahasiswa {
        return Mahasiswa(
            nama = "Nama: Dessy Nurulita",
            nim = "NIM: 2310817220024",
            prodi = "Program Studi: Teknologi Informasi",
            ttl = "Tempat, Tanggal Lahir: Banjarmasin, 22 Desember 2004",
            pendidikan = "Universitas Lambung Mangkurat",
            minat = "Minat: Software Engineer",
            organisasi = "Organisasi: Anggota KOMINFO UKM PP FIM ULM 2024 dan Bendahara Umum UKM PP FIM ULM 2025",
            tujuan = "Tujuan: Sukses Dunia dan Akhirat",
            foto = R.drawable.foto1
        )
    }
}
