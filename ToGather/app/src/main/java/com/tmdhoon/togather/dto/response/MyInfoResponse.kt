package com.tmdhoon.togather.dto.response

data class MyInfoResponse(
    val name: String,
    val email : String,
    val profile_image_url : String,
    val introduce : String,
    val position : ArrayList<String>,
)