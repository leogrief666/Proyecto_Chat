package com.example.proyecto_chat.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.proyecto_chat.data.Mensaje
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import coil.compose.rememberAsyncImagePainter

@Composable
fun MessageBubble(message: Mensaje, isUser: Boolean, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start, // Alineación a la derecha o izquierda
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp) // Espacio entre los mensajes y el borde
    ) {
        if (message.text != null) {
            // Muestra el mensaje de texto
            Text(
                text = message.text,
                color = if (isUser) Color.White else Color.Black,
                modifier = Modifier
                    .background(if (isUser) Color.Blue else Color.Gray, RoundedCornerShape(8.dp))
                    .padding(8.dp)
            )
        } else if (message.imageUri != null) {
            // Muestra la imagen desde la URI
            Image(
                painter = rememberAsyncImagePainter(message.imageUri),
                contentDescription = "Imagen adjunta",
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp) // Espacio adicional entre la imagen y los bordes
            )
        } else if (message.imageBitmap != null) {
            // Muestra la imagen desde el Bitmap
            Image(
                bitmap = message.imageBitmap.asImageBitmap(),
                contentDescription = "Imagen tomada con la cámara",
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp) // Espacio adicional entre la imagen y los bordes
            )
        }
    }
}



