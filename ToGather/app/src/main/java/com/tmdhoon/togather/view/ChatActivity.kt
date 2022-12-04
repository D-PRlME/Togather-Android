package com.tmdhoon.togather.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.tmdhoon.togather.R
import com.tmdhoon.togather.base.BaseActivity
import com.tmdhoon.togather.databinding.ActivityChatBinding
import com.tmdhoon.togather.network.SocketProvider
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.util.getPref
import com.tmdhoon.togather.util.initPref
import com.tmdhoon.togather.util.printToast
import com.tmdhoon.togather.viewmodel.ChatViewModel
import io.socket.client.Manager
import io.socket.client.Socket
import io.socket.engineio.client.Transport

import org.json.JSONObject

class ChatActivity : BaseActivity<ActivityChatBinding>(R.layout.activity_chat) {

    private val socket: Socket by lazy {
        SocketProvider.getSocket()
    }

    private val pref by lazy {
        initPref(
            context = this,
            mode = MODE_PRIVATE,
        )
    }

    private val chatViewModel by lazy {
        ViewModelProvider(this).get(ChatViewModel::class.java)
    }

    private val userName by lazy {
        getPref(pref, "userName", "")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        connectSocket()
        initBackButton()
        initNameTextView()
        joinRoom()
        initSendButton()
        observeMessage()
    }

    private fun initSendButton(){
        binding.btChatSend.setOnClickListener {
            val message = binding.etChatMessage.text.toString()
            if(message.isNotEmpty()){
                chatViewModel.sendMessage(message)
                binding.etChatMessage.text = null
            }
        }
    }

    private fun observeMessage(){
        chatViewModel.message.observe(this){

        }
    }

    private fun joinRoom(){
        chatViewModel.joinRoom(
            is_join_room = true,
            room_id = Integer.parseInt(getPref(pref, userName.toString(), 0).toString())
        )
    }

    private fun initNameTextView() {
        binding.tvChatName.text = getPref(
            preferences = pref,
            key = "userName",
            value = "",
        ).toString()
    }

    fun connectSocket() {
        chatViewModel.connectSocket()
    }

    private fun initBackButton() {
        binding.imgChatBack.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        chatViewModel.disconnectSocket()
    }
}