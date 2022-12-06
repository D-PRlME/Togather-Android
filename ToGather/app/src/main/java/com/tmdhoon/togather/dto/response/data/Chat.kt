package com.tmdhoon.togather.dto.response.data

data class Chat(
    val room_id : Int,
    val user : User,
    val is_mine : Boolean,
    val message : String,
    val sent_at : String,
    val sent_date : String,
)
