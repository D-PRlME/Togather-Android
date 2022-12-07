package com.tmdhoon.togather.dto.response

import com.tmdhoon.togather.dto.response.data.Chat

data class RoomListResponse(
    val room_list : ArrayList<Room>
)

data class Room(
    val room_id : Int,
    val room_type : String,
    val room_logo_image : String,
    val room_name : String,
    val last_chat : LastChat,
    val is_read : Boolean
)

data class LastChat(
    val last_message : String,
    val last_send_at : String,
)
