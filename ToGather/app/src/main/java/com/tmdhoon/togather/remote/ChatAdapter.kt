package com.tmdhoon.togather.remote

import android.util.Log
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

    companion object{
        const val MY_CHAT = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == MY_CHAT){
            return MyChatViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.list_message_my,
                    parent,
                    false
                ),
            )
        }else {
            return OtherChatViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.list_message_other,
                    parent,
                    false
                ),
            )
        }
    }

    class MyChatViewHolder(
        val binding : ListMessageMyBinding,
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(chat : Chat){
            binding.model = chat
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(chatList[position].is_mine) 1 else 2
    }

    class OtherChatViewHolder(
        val binding : ListMessageOtherBinding,
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