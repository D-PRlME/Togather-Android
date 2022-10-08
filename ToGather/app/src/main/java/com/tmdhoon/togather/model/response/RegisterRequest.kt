package com.tmdhoon.togather.model.response

data class RegisterRequest(
    val email: String,
    val name: String,
    val password: String
)