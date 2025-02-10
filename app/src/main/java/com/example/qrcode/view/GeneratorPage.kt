package com.example.qrcode.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.qrcode.viewmodel.QRCodeViewModel

@Composable
fun GeneratorPage(modifier: Modifier, navController: NavController, viewModel: QRCodeViewModel) {
    var input by remember { mutableStateOf("") }
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Generate QR Code",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(20.dp)
            )

            OutlinedTextField(
                value = input,
                onValueChange = {
                    input = it
                },
                label = { Text(text = "Enter Text or url") },
                modifier = Modifier.padding(20.dp)
            )

            Button(
                onClick = {
                    if (input.isNotEmpty()) {
                        viewModel.generateQRCode(input)
                        navController.navigate("GeneratedQRPage")
                    }
                },
                modifier = Modifier.padding(20.dp)
            ) {
                Text(text = "Generate")
            }
        }
    }
}
