package com.tmdhoon.togather.repository

import android.util.Log
import com.tmdhoon.togather.network.SocketProvider
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.viewmodel.ChatViewModel
import io.socket.client.Manager
import io.socket.client.Socket
import io.socket.engineio.client.Transport
import org.json.JSONObject

class ChatRepository(
    private val chatViewModel : ChatViewModel,
) {

    private val socket by lazy {
        SocketProvider.getSocket()
    }

    fun connectSocket() {
        socket.io().on(Manager.EVENT_TRANSPORT) { args ->
            val trans = args[0] as Transport
            trans.on(Transport.EVENT_REQUEST_HEADERS) { args ->
                val mHeader = args[0] as MutableMap<String, List<String>>
                mHeader["Authorization"] = listOf("Bearer $ACCESS_TOKEN")
            }
        }
        socket.connect()
    }

    fun joinRoom(
        obj : JSONObject,
    ){
        socket.emit("join", obj)
        socket.on("room"){args->
            val join = JSONObject(args[0].toString())
            if(args[0] != null){
                val joinRoom = args[0].toString()
                Log.d("TEST", joinRoom)
            }
        }
    }

    fun sendMessage(
        obj : JSONObject,
    ){
        socket.emit("chat", obj)
        socket.on("chat"){args->
            val message = JSONObject(args[0].toString())
            chatViewModel.message.postValue(message.getString("message"))
        }
    }

    fun disconnectSocket(){
        socket.disconnect()
    }



}