package com.tmdhoon.togather.dto.response

data class MyInfoResponse(
    val name: String,
    val email : String,
    val profile_image_url : String,
    val positions : ArrayList<String>,
    val introduce : String,
)
