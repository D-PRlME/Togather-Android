package com.tmdhoon.togather.repository

import android.util.Log
import com.tmdhoon.togather.util.ACCESS_TOKEN
import io.socket.client.Manager
import io.socket.client.Socket
import io.socket.engineio.client.Transport

class ChatRepository {
    fun startSocket(socket : Socket){
        socket.io().on(Manager.EVENT_TRANSPORT) {args ->
            val trans = args[0] as Transport
            trans.on(Transport.EVENT_REQUEST_HEADERS){
                    args->
                val mHeaders = args[0] as MutableMap<String, List<String>>
                mHeaders["Authorization"] = listOf("Bearer $ACCESS_TOKEN")
            }
        }
        socket.on(Socket.EVENT_CONNECT){
            Log.d("TEST", "success")

        }.on(Socket.EVENT_CONNECT_ERROR){
            Log.d("TEST", it.contentToString())
        }
        socket.connect()
    }
}