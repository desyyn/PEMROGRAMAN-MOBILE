package com.example.mydicerollerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mydicerollerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var lastDice1 = 0
    private var lastDice2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.resultText.setBackgroundResource(R.drawable.result_background)

        lastDice1 = savedInstanceState?.getInt("DICE1") ?: 0
        lastDice2 = savedInstanceState?.getInt("DICE2") ?: 0

        if (lastDice1 != 0 && lastDice2 != 0) {
            binding.diceImage1.setImageResource(getDiceDrawable(lastDice1))
            binding.diceImage2.setImageResource(getDiceDrawable(lastDice2))
            binding.resultText.text = if (lastDice1 == lastDice2) {
                "Selamat anda dapat dadu double!"
            } else {
                "Maaf anda belum beruntung"
            }
        }

        binding.rollButton.setOnClickListener {
            val randomInt1 = (1..6).random()
            val randomInt2 = (1..6).random()

            lastDice1 = randomInt1
            lastDice2 = randomInt2

            binding.diceImage1.setImageResource(getDiceDrawable(randomInt1))
            binding.diceImage2.setImageResource(getDiceDrawable(randomInt2))

            binding.resultText.text = if (randomInt1 == randomInt2) {
                "Selamat anda dapat dadu double!"
            } else {
                "Maaf anda belum beruntung"
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("DICE1", lastDice1)
        outState.putInt("DICE2", lastDice2)
    }

    private fun getDiceDrawable(number: Int): Int {
        return when (number) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_0
        }
    }
}
