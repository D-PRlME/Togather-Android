package com.tmdhoon.togather.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmdhoon.togather.R
import com.tmdhoon.togather.base.BaseActivity
import com.tmdhoon.togather.databinding.ActivityChatBinding
import com.tmdhoon.togather.dto.response.data.Chat
import com.tmdhoon.togather.dto.response.data.User
import com.tmdhoon.togather.remote.ChatAdapter
import com.tmdhoon.togather.util.getPref
import com.tmdhoon.togather.util.initPref
import com.tmdhoon.togather.viewmodel.ChatViewModel

import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class ChatActivity : BaseActivity<ActivityChatBinding>(R.layout.activity_chat) {

    private val chatList: ArrayList<Chat> by lazy {
        ArrayList()
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

    private fun initSendButton() {
        binding.btChatSend.setOnClickListener {
            val message = binding.etChatMessage.text.toString()
            if (message.isNotEmpty()) {
                chatList.add(
                    Chat(
                        room_id = 0,
                        user = User(0, "", ""),
                        is_mine = true,
                        message = message,
                        send_at = "방금전",
                        send_date = "",
                    )
                )
                initRecyclerView(chatList)
                chatViewModel.sendMessage(message)
                binding.etChatMessage.text = null
            }
        }
    }

    private fun observeMessage() {
        chatViewModel.chat.observe(this) {
            initRecyclerView(chatList)
        }
    }

    private fun initRecyclerView(chatList: ArrayList<Chat>) {
        binding.rvChat.run {
            adapter = ChatAdapter(
                chatList = chatList,
                chatViewModel = chatViewModel,
            )
            layoutManager = LinearLayoutManager(this@ChatActivity)
        }
    }

    private fun joinRoom() {
        chatViewModel.joinRoom(
            is_join_room = true,
            room_id = getPref(pref, userName.toString(), 0) as Int
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