package com.tmdhoon.togather.dto.request

data class AccountEditRequest(
    val name : String,
    val profile_image_url : String,
    val introduce : String,
    val positions : ArrayList<String>,
)
