package com.tmdhoon.togather.dto.response

import com.tmdhoon.togather.dto.response.data.User

data class ChatResponse(
    val room_id : Int,
    val user : User,
    val is_mine : Boolean,
    val message : String,
    val send_at : String,
)
