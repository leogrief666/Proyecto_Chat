package com.example.proyecto_chat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.proyecto_chat.ui.screens.ChatScreen
import com.example.proyecto_chat.ui.theme.Proyecto_ChatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proyecto_ChatTheme {
                ChatScreen()
            }
        }
    }
}
