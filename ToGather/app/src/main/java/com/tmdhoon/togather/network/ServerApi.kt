package com.tmdhoon.togather.network

import com.tmdhoon.togather.model.request.LoginRequest
import com.tmdhoon.togather.model.response.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ServerApi {

    @POST("users/auth")
    fun login(
        @Body loginRequest: LoginRequest
    ) : Call<LoginResponse>

}