package com.tmdhoon.togather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmdhoon.togather.repository.ChatRepository
import com.tmdhoon.togather.util.getPref
import org.json.JSONObject

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

    val message : MutableLiveData<String> = MutableLiveData()

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
}