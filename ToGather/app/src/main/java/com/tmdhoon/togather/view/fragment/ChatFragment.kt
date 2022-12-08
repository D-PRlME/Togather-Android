package com.tmdhoon.togather.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentChatBinding
import com.tmdhoon.togather.dto.response.Room
import com.tmdhoon.togather.remote.RoomListAdapter
import com.tmdhoon.togather.viewmodel.ChatViewModel

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding

    private val chatViewModel : ChatViewModel by lazy {
        ViewModelProvider(this).get(ChatViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false)
        binding.lifecycleOwner = this

        getRoomList()
        observeRoomListResponse()

        return binding.root
    }

    private fun initRecyclerView(roomList : ArrayList<Room>) {
        binding.rvChatRoomList.run {
            adapter = RoomListAdapter(
                roomList = roomList,
                context = requireContext(),
            )
            layoutManager = LinearLayoutManager(this@ChatFragment.requireContext())
        }
    }

    private fun getRoomList() {
        chatViewModel.getRoomList()
    }

    private fun observeRoomListResponse(){
        chatViewModel.roomList.observe(viewLifecycleOwner){
            when(it.code()){
                200->{
                    initRecyclerView(it.body()!!.room_list)
                }
            }
        }
    }
}