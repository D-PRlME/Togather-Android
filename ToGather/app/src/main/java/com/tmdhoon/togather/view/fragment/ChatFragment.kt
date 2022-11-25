package com.tmdhoon.togather.view.fragment

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentChatBinding
import com.tmdhoon.togather.databinding.FragmentHomeBinding
import com.tmdhoon.togather.network.SocketProvider
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.util.BASE_URL
import com.tmdhoon.togather.viewmodel.ChatViewModel
import io.socket.client.IO
import io.socket.client.Manager
import io.socket.client.Socket
import io.socket.emitter.Emitter
import io.socket.engineio.client.Transport

class ChatFragment : Fragment() {

    private lateinit var binding : FragmentChatBinding

    private val chatViewModel : ChatViewModel by lazy {
        ChatViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false)

        binding.lifecycleOwner = this
        chatViewModel.startSocket(SocketProvider.getSocket())

        return binding.root
    }
}