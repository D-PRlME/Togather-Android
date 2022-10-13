package com.tmdhoon.togather.model.response

data class RegisterRequest(
    val password : String,
    val name : String,
    val email : String,
)