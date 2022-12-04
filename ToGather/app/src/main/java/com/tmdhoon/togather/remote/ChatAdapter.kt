package com.tmdhoon.togather.remote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ListMessageMyBinding
import com.tmdhoon.togather.databinding.ListMessageOtherBinding
import com.tmdhoon.togather.dto.response.data.Chat
import com.tmdhoon.togather.viewmodel.ChatViewModel
import org.json.JSONObject

class ChatAdapter(
    private val chatList : ArrayList<Chat>,
    private val chatViewModel : ChatViewModel,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 0){
            return MyChatViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.list_message_my,
                    parent,
                    false
                ),
                chatViewModel = chatViewModel,
            )
        }else{
            return OtherChatViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.list_message_other,
                    parent,
                    false
                ),
                chatViewModel = chatViewModel
            )
        }
    }

    class MyChatViewHolder(
        val binding : ListMessageMyBinding,
        val chatViewModel : ChatViewModel
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(chat : Chat ){
            binding.model = chat
        }
    }

    class OtherChatViewHolder(
        val binding : ListMessageOtherBinding,
        val chatViewModel : ChatViewModel,
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(chat : Chat){
            binding.model = chat
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(chatList[position].is_mine){
            true -> (holder as MyChatViewHolder).bind(chatList[position])
            false -> (holder as OtherChatViewHolder).bind(chatList[position])
        }
    }

    override fun getItemCount(): Int =
        chatList.size

}