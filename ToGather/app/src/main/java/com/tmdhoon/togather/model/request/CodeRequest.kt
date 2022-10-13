package com.tmdhoon.togather.model.request

data class CodeRequest(
    val email : String,
    val auth_code : String,
)
