package com.tmdhoon.togather.model.response

import retrofit2.Call

data class LoginResponse(
    val access_token: String,
    val expired_at: String,
    val refresh_token: String
)
