package com.tmdhoon.togather.dto.response

data class UserInfoResponse(
    val user_id : Int,
    val name : String,
    val email : String,
    val profile_image_url : String,
    val introduce : String,
    val positions : ArrayList<String>,
)
