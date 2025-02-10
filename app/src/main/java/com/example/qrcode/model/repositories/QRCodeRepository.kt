package com.example.qrcode.model.repositories

import com.example.qrcode.model.api.APIInstance

class QRCodeRepository {
    private val apiService = APIInstance.api

    suspend fun generateQRCode(data: String): ByteArray {
        return apiService.generateQRCode(data).body()?.bytes() ?: ByteArray(0)
    }

}