package com.tmdhoon.togather.model.request

data class AccountEditRequest(
    val user_name : String,
    val profile_image_url : String,
    val introduce : String,
    val positions : List<String>
)
