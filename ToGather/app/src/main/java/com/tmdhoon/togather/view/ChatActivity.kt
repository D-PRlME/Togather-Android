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

    private val chatList : ArrayList<Chat> by lazy {
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

    private val dateFormat : SimpleDateFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    }

    private val timeFormat : SimpleDateFormat by lazy {
        SimpleDateFormat("k:mm:ss")
    }

    private val getDate by lazy {
        dateFormat.format(Date(System.currentTimeMillis()))
    }

    private val getTime by lazy {
        timeFormat.format(System.currentTimeMillis())
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
        observeChattingList()
        getChattingList()
        chatViewModel.parsingMessage()
    }

    private fun getChattingList() {
        chatViewModel.getChattingList(
            roomId = getPref(pref, userName.toString(), 0) as Int,
            time = "${getDate}T${getTime}"
        )
    }

    private fun observeChattingList(){
        chatViewModel.chatList.observe(this){
            when(it.code()){
                200 -> initRecyclerView(it.body()!!.chat_list)
            }
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

    private fun observeMessage() {
        chatViewModel.chat.observe(this) {

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
        binding.rvChat.adapter?.notifyDataSetChanged()
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