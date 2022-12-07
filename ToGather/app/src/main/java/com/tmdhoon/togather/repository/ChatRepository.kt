package com.tmdhoon.togather.repository

import com.tmdhoon.togather.dto.response.ChattingResponse
import com.tmdhoon.togather.dto.response.data.Chat
import com.tmdhoon.togather.dto.response.data.User
import com.tmdhoon.togather.network.ApiProvider
import com.tmdhoon.togather.network.SocketProvider
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.viewmodel.ChatViewModel
import io.socket.client.Manager
import io.socket.engineio.client.Transport
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatRepository(
    private val chatViewModel : ChatViewModel,
) {

    private val socket by lazy {
        SocketProvider.getSocket()
    }

    private var chatList : ArrayList<Chat> = ArrayList()

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
        socket.emit("chat2", obj)
    }

    fun getMessage(){
        socket.on("chat"){args->
            val message = JSONObject(args[0].toString())
            val user = JSONObject(message.getString("user"))
            chatList.add(Chat(
                room_id = message.getInt("room_id"),
                user = User(
                    user_id = user.getLong("user_id"),
                    user_name = user.getString("user_name"),
                    profile_image_url = user.getString("profile_image_url")
                ),
                is_mine = message.getBoolean("is_mine"),
                message = message.getString("message"),
                sent_at = message.getString("sent_at"),
                sent_date = message.getString("sent_date")
                )
            )
            chatViewModel.chatList.postValue(chatList)
        }
    }

    fun disconnectSocket(){
        socket.disconnect()
    }

    fun getChattingList(
        roomId : Long,
        time : String,
    ){
        ApiProvider.retrofit.getChat(
            accessToken = "Bearer $ACCESS_TOKEN",
            roomId = roomId,
            time = time,
        ).enqueue(object : Callback<ChattingResponse> {
            override fun onResponse(
                call: Call<ChattingResponse>,
                response: Response<ChattingResponse>
            ) {
                chatList = response.body()!!.chat_list
                chatViewModel.chatList.value = chatList
            }

            override fun onFailure(call: Call<ChattingResponse>, t: Throwable) {
            }
        })
    }
}