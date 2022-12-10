package com.tmdhoon.togather.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmdhoon.togather.R
import com.tmdhoon.togather.base.BaseActivity
import com.tmdhoon.togather.databinding.ActivityChatBinding
import com.tmdhoon.togather.dto.response.data.Chat
import com.tmdhoon.togather.remote.ChatAdapter
import com.tmdhoon.togather.util.hideKeyBoard
import com.tmdhoon.togather.util.initPref
import com.tmdhoon.togather.viewmodel.ChatViewModel
import java.text.SimpleDateFormat
import java.util.*

class ChatActivity : BaseActivity<ActivityChatBinding>(R.layout.activity_chat) {

    private val chatViewModel by lazy {
        ViewModelProvider(this).get(ChatViewModel::class.java)
    }

    private val dateFormat : SimpleDateFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss", Locale.KOREA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        setView()
        setServer()
    }

    private fun setServer(){
        connectSocket()
        joinRoom()
        getChattingList()
        observeChattingList()
        chatViewModel.getMessage()
    }

    private fun setView(){
        initNameTextView()
        initSendButton()
        initBackButton()
    }

    private fun getChattingList() {
        chatViewModel.getChattingList(
            roomId = intent.getIntExtra("roomId", 0),
            time = dateFormat.format(Date(System.currentTimeMillis()))
        )
    }

    private fun observeChattingList(){
        chatViewModel.chatList.observe(this){
            initRecyclerView(it)
        }
    }

    private fun initSendButton() {
        binding.btChatSend.setOnClickListener {
            val message = binding.etChatMessage.text.toString()
            if (message.isNotEmpty()) {
                hideKeyBoard(applicationContext, binding.root)
                chatViewModel.sendMessage(message)
                binding.etChatMessage.text = null
            }
        }
    }

    private fun initRecyclerView(chatList : ArrayList<Chat>) {
        binding.rvChat.run {
            adapter = ChatAdapter(
                chatList = chatList,
                chatViewModel = chatViewModel,
            )
            layoutManager = LinearLayoutManager(this@ChatActivity).apply {
                this.stackFromEnd = true
            }
        }
    }

    private fun joinRoom() {
        chatViewModel.joinRoom(
            is_join_room = true,
            room_id = intent.getIntExtra("roomId", 0)
        )
    }

    private fun initNameTextView() {
        binding.tvChatName.text = intent.getStringExtra("userName")
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