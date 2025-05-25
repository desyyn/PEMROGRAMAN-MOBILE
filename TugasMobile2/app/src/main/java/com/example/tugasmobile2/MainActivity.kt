package com.example.tugasmobile2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)

        val url = "https://tugasmobile2-des.free.beeceptor.com/data"

        val queue = Volley.newRequestQueue(this)

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response: JSONObject ->
                try {
                    val nama = response.getString("nama")
                    val nim = response.getString("nim")
                    val programStudi = response.getString("programStudi")
                    val semester = response.getString("semester")

                    val resultText = "Nama: $nama\nNIM: $nim\nProgram Studi: $programStudi\nSemester: $semester"
                    tvResult.text = resultText
                } catch (e: Exception) {
                    tvResult.text = "JSON parsing error: ${e.message}"
                }
            },
            { error ->
                tvResult.text = "Request error: ${error.message}"
            }
        )

        queue.add(request)
    }
}
