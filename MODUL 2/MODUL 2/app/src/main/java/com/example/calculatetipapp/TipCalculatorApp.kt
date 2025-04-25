package com.example.calculatetipapp

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipCalculatorApp(tipViewModel: TipViewModel = viewModel()) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Tip Time",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF6200EE))
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            TextField(
                value = tipViewModel.serviceCostInput,
                onValueChange = { tipViewModel.serviceCostInput = it },
                placeholder = {
                    if (tipViewModel.serviceCostInput.isEmpty()) {
                        Text("Cost of Service", color = Color.Gray)
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text("How was the service?", color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))
            listOf(
                "Amazing (20%)" to 20,
                "Good (18%)" to 18,
                "Okay (15%)" to 15
            ).forEach { (label, value) ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = tipViewModel.selectedTipPercent == value,
                        onClick = { tipViewModel.selectedTipPercent = value }
                    )
                    Text(text = label)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Round up tip?")
                Switch(
                    checked = tipViewModel.roundUp,
                    onCheckedChange = { tipViewModel.roundUp = it }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val cost = tipViewModel.serviceCostInput.toDoubleOrNull()
                    if (cost == null || cost <= 0.0) {
                        Toast.makeText(context, "Jangan masukkan nilai 0 atau minus!", Toast.LENGTH_SHORT).show()
                        tipViewModel.showTip = false
                    } else {
                        tipViewModel.onCalculateClicked()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("CALCULATE")
            }

            if (!tipViewModel.showTip) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Tip Amount",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            if (tipViewModel.showTip) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Tip Amount: \$${tipViewModel.calculatedTip}",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}