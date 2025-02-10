package com.example.qrcode.view

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.qrcode.viewmodel.QRCodeViewModel
import java.io.File
import java.io.FileOutputStream

@Composable
fun GeneratedQRPage(modifier: Modifier, viewModel: QRCodeViewModel, context: Context) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (viewModel.qrBitmap.value != null) {
                Image(
                    bitmap = viewModel.qrBitmap.value!!.asImageBitmap(),
                    contentDescription = "QR Code",
                    modifier = Modifier.size(200.dp)
                )
            } else if (viewModel.errorMessage.value != null) {
                Text(
                    text = viewModel.errorMessage.value!!,
                    modifier = Modifier.padding(20.dp)
                )
            }

            Button(
                onClick = {
                    viewModel.qrBitmap.value?.let { saveQRCode(it, context) }
                },
                modifier = Modifier.padding(20.dp)
            ) {
                Text(text = "Download")
            }
        }
    }
}

fun saveQRCode(value: Bitmap, context: Context) {
    val savedImagePath: String?
    val imageFileName = "QR" + System.currentTimeMillis() + ".png"
    val storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)

    var success = true
    if (!storageDir.exists()) {
        success = storageDir.mkdirs()
    }

    if (success) {
        val imageFile = File(storageDir, imageFileName)
        savedImagePath = imageFile.absolutePath
        try {
            val fOut = FileOutputStream(imageFile)
            value.compress(Bitmap.CompressFormat.PNG, 100, fOut)
            fOut.close()
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
        }

        val f = savedImagePath?.let { File(it) }
        f?.let {
            MediaScannerConnection.scanFile(context, arrayOf(f.path), null, null)
        }
        Toast.makeText(context, "Image saved", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(context, "Error saving image", Toast.LENGTH_SHORT).show()
        }

}
