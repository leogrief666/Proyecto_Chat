package com.example.proyecto_chat.viewmodel

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_chat.data.Mensaje
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    // Lista de mensajes como StateFlow para que los cambios se reflejen en la UI
    private val _mensajes = MutableStateFlow<List<Mensaje>>(emptyList())
    val mensajes: StateFlow<List<Mensaje>> get() = _mensajes

    // Método para enviar un mensaje de texto
    fun sendMessage(text: String) {
        if (text.isNotBlank()) {
            val newMessage = Mensaje(text = text, sender = "You")
            addMessage(newMessage)
        }
    }

    // Método para enviar una imagen desde URI
    fun sendImage(imageUri: Uri) {
        viewModelScope.launch {
            val newMessage = Mensaje(imageUri = imageUri, sender = "You")
            addMessage(newMessage)
        }
    }

    // Método para enviar una imagen desde Bitmap (usado para cámara)
    fun sendImage(imageBitmap: Bitmap) {
        viewModelScope.launch {
            val newMessage = Mensaje(imageBitmap = imageBitmap, sender = "You")
            addMessage(newMessage)
        }
    }

    // Agrega el mensaje a la lista de mensajes
    private fun addMessage(message: Mensaje) {
        _mensajes.value = _mensajes.value + message
    }
}


