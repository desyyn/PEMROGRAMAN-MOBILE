package com.example.calculatetipapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import com.example.calculatetipapp.ui.theme.CalculateTipAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculateTipAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TipCalculatorApp()
                }
            }
        }
    }
}