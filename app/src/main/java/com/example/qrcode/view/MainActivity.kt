package com.example.qrcode.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.qrcode.ui.theme.QRCodeTheme
import com.example.qrcode.viewmodel.QRCodeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QRCodeTheme {
                val viewModel = ViewModelProvider(this)[QRCodeViewModel::class.java]
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "HomePage", builder = {
                        composable("HomePage") {
                            HomePage(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable("GeneratorPage") {
                            GeneratorPage(modifier = Modifier.padding(innerPadding), navController, viewModel)
                        }
                        composable("GeneratedQRPage") {
                            GeneratedQRPage(modifier = Modifier.padding(innerPadding), viewModel, this@MainActivity)
                        }
                    })
                }
            }
        }
    }
}