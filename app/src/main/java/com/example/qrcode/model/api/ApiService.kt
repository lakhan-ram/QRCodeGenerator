package com.example.qrcode.model.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("create-qr-code")
    suspend fun generateQRCode(
        @Query("data") data: String
    ): Response<ResponseBody>

}