package com.tmdhoon.togather.viewmodel

import androidx.lifecycle.ViewModel
import com.tmdhoon.togather.repository.ChatRepository
import io.socket.client.IO
import io.socket.client.Socket

class ChatViewModel() : ViewModel() {
    private val chatRepository: ChatRepository by lazy {
        ChatRepository()
    }

    fun startSocket(socket : Socket){
        val option = IO.Options()
        option.transports = arrayOf(
            io.socket.engineio.client.transports.WebSocket.NAME
        )
        chatRepository.startSocket(
            IO.socket(
            "http://52.55.240.35:8081",
                option,
            )
        )
    }
}