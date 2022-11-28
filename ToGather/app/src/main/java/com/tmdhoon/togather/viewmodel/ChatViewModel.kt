package com.tmdhoon.togather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmdhoon.togather.network.SocketProvider
import com.tmdhoon.togather.repository.ChatRepository
import com.tmdhoon.togather.util.ACCESS_TOKEN
import io.socket.client.Manager
import io.socket.client.Socket
import io.socket.engineio.client.Transport

class ChatViewModel : ViewModel() {
    private val socket by lazy {
        SocketProvider.getSocket()
    }

    private val chatRepository by lazy {
        ChatRepository(
            chatViewModel = this,
            socket = socket,
        )
    }

    fun connectSocket(){
        socket.on(Socket.EVENT_CONNECT){
            Log.d("TEST", "connect success")
            Log.d("TEST", it.contentToString())
        }
        chatRepository.connectSocket()
    }

    fun disconnectSocket(){
        socket.on(Socket.EVENT_DISCONNECT){
            Log.d("TEST", "disconnect success")
        }
        chatRepository.disconnectSocket()
    }






}