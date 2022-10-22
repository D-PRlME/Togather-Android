package com.tmdhoon.togather.dto.response

data class RegisterRequest(
    val password : String,
    val name : String,
    val email : String,
)