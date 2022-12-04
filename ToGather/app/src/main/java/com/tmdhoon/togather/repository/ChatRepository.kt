package com.tmdhoon.togather.repository

import android.util.Log
import com.tmdhoon.togather.dto.response.data.Chat
import com.tmdhoon.togather.dto.response.data.User
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
    }

    fun sendMessage(
        obj : JSONObject,
    ){
        socket.emit("chat", obj)
        socket.on("chat"){args->
            val message = JSONObject(args[0].toString())
            chatViewModel.chat.postValue(Chat(
                room_id = message.getInt("room_id"),
                user = User(
                    user_id = message.getInt("user_id"),
                    user_name = message.getString("user_name"),
                    profile_image_url = message.getString("profile_image_url"),
                ),
                is_mine = message.getBoolean("is_mine"),
                message = message.getString("message"),
                send_at = message.getString("send_at"),
                send_date = message.getString("send_data")
            ))
        }
    }

    fun disconnectSocket(){
        socket.disconnect()
    }



}