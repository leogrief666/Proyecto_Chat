package com.example.proyecto_chat.data

import android.graphics.Bitmap
import android.net.Uri

data class Mensaje(
    val text: String? = null,        // Para mensajes de texto
    val imageUri: Uri? = null,        // Para imágenes de la galería
    val imageBitmap: Bitmap? = null,  // Para imágenes de la cámara
    val sender: String
)
