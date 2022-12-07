package com.tmdhoon.togather.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.tmdhoon.togather.dto.response.ChattingResponse
import com.tmdhoon.togather.dto.response.data.Chat
import com.tmdhoon.togather.repository.ChatRepository
import com.tmdhoon.togather.util.getPref
import org.json.JSONObject
import retrofit2.Response

class ChatViewModel : ViewModel(){
    private val chatRepository : ChatRepository by lazy {
        ChatRepository(this)
    }

    private val roomObject by lazy {
        JSONObject()
    }

    private val chatObject by lazy {
        JSONObject()
    }

    val chatList : MutableLiveData<ArrayList<Chat>> = MutableLiveData()

    fun connectSocket(){
        chatRepository.connectSocket()
    }

    fun disconnectSocket(){
        chatRepository.disconnectSocket()
    }

    fun joinRoom(
        is_join_room : Boolean,
        room_id : Int,
    ){
        roomObject.put("is_join_room", is_join_room)
        roomObject.put("room_id", room_id)
        chatRepository.joinRoom(roomObject)
    }

    fun sendMessage(
        message : String,
    ){
        chatObject.put("message", message)
        chatRepository.sendMessage(chatObject)
    }

    fun getChattingList(
        roomId : Int,
        time : String,
    ){
        chatRepository.getChattingList(
            roomId = roomId.toLong(),
            time = time
        )
    }

    fun getMessage(){
        chatRepository.getMessage()
    }
}