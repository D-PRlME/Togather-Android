package com.tmdhoon.togather.view

import android.os.Bundle
import android.os.LocaleList
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
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import kotlin.collections.ArrayList

class ChatActivity : BaseActivity<ActivityChatBinding>(R.layout.activity_chat) {


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

    private val dateFormat : SimpleDateFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    }

    private val timeFormat : SimpleDateFormat by lazy {
        SimpleDateFormat("k:mm:ss")
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
            roomId = getPref(pref, userName.toString(), 0) as Int,
            time = "${dateFormat.format(Date(System.currentTimeMillis()))}T${timeFormat.format(Date(System.currentTimeMillis()))}"
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