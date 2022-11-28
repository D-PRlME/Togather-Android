package com.tmdhoon.togather.repository

import android.util.Log
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.viewmodel.ChatViewModel
import io.socket.client.Manager
import io.socket.client.Socket
import io.socket.engineio.client.Transport

class ChatRepository(
    private val chatViewModel : ChatViewModel,
    private val socket : Socket,
) {

    fun connectSocket(){
        socket.io().on(Manager.EVENT_TRANSPORT){ args->
            val trans = args[0] as Transport
            trans.on(Transport.EVENT_REQUEST_HEADERS){
                    args->
                val mHeader = args[0] as MutableMap<String, List<String>>
                mHeader["Authorization"] = listOf("Bearer $ACCESS_TOKEN")
            }
        }
        socket.connect()
    }

    fun disconnectSocket(){
        socket.disconnect()
    }
}