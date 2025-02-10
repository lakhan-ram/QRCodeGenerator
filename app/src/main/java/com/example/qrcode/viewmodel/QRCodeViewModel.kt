package com.example.qrcode.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.qrcode.model.repositories.QRCodeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QRCodeViewModel: ViewModel() {
    private val repository = QRCodeRepository()
    val qrBitmap by lazy { mutableStateOf<Bitmap?>(null) }
    val errorMessage by lazy { mutableStateOf<String?>(null) }

    fun generateQRCode(data: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val qrCodeBytes = repository.generateQRCode(data)
                qrBitmap.value = BitmapFactory.decodeByteArray(qrCodeBytes, 0, qrCodeBytes.size)
            } catch (e: Exception) {
                errorMessage.value = e.localizedMessage
            }

        }
    }
}