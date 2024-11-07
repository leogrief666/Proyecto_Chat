package com.example.proyecto_chat.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.proyecto_chat.ui.components.MessageBubble
import com.example.proyecto_chat.viewmodel.ChatViewModel
import androidx.compose.foundation.lazy.rememberLazyListState

@Composable
fun ChatScreen(chatViewModel: ChatViewModel = remember { ChatViewModel() }) {
    val messages by chatViewModel.mensajes.collectAsState()

    // Lanzador para abrir la galería
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let { chatViewModel.sendImage(it) }
        }
    )

    // Lanzador para abrir la cámara
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { bitmap ->
            bitmap?.let { chatViewModel.sendImage(bitmap) }
        }
    )

    Column(modifier = Modifier.fillMaxSize()) {
        // Encabezado con el nombre del contacto
        Text(
            text = "Nombre del contacto",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = Color.Black
        )

        // Lista de mensajes
        val listState = rememberLazyListState()

        LaunchedEffect(messages.size) {
            listState.animateScrollToItem(messages.size) // Desplazarse al último mensaje
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp) // Espaciado entre los mensajes
        ) {
            items(messages) { message ->
                // Agregar espaciado entre las burbujas de los mensajes
                MessageBubble(message = message, isUser = message.sender == "You")
            }
        }

        // Campo de entrada con placeholder y botón de adjuntar imagen
        var inputText by remember { mutableStateOf("") }
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    galleryLauncher.launch("image/*")
                }
            ) {
                Icon(Icons.Default.Photo, contentDescription = "Adjuntar imagen", tint = Color.Black)
            }

            BasicTextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                    .padding(8.dp),
                singleLine = true,
                decorationBox = { innerTextField ->
                    if (inputText.isEmpty()) {
                        Text(
                            text = "Escribe un mensaje",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                    }
                    innerTextField()
                }
            )

            Button(
                onClick = {
                    chatViewModel.sendMessage(inputText)
                    inputText = ""
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Send")
            }
        }
    }
}
