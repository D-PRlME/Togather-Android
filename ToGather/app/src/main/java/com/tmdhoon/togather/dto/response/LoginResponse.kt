package com.tmdhoon.togather.dto.response

data class LoginResponse(
    val access_token: String,
    val expired_at: String,
    val refresh_token: String,
)
