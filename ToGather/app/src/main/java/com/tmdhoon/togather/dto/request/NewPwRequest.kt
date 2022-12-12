package com.tmdhoon.togather.dto.request

data class NewPwRequest(
    val new_password : String,
    val email : String = "jsh0911@dsm.hs.kr"
)
