package com.tmdhoon.togather.remote

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ListRoomBinding
import com.tmdhoon.togather.dto.response.Room
import com.tmdhoon.togather.util.startIntent
import com.tmdhoon.togather.view.ChatActivity

class RoomListAdapter(
    private val roomList: ArrayList<Room>,
    private val context : Context,
) : RecyclerView.Adapter<RoomListAdapter.RoomListViewHolder>() {
    class RoomListViewHolder(
        val binding: ListRoomBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(room: Room) {
            binding.model = room
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomListViewHolder =
        RoomListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_room,
                parent,
                false,
            ),
        )

    override fun onBindViewHolder(holder: RoomListViewHolder, position: Int) {
        holder.bind(roomList[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("roomId", roomList[position].room_id)
            intent.putExtra("userName", roomList[position].room_name)
            this.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int =
        roomList.size
}