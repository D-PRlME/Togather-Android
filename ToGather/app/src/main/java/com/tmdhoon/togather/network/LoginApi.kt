package com.tmdhoon.togather.network

import com.tmdhoon.togather.dto.request.LoginRequest
import com.tmdhoon.togather.dto.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginApi {

    // 로그인
    @POST("users/auth")
    fun login(
        @Body loginRequest: LoginRequest,
    ): Call<LoginResponse>

    // 로그아웃
    @DELETE("users/logout")
    fun logout(
        @Header("Authorization") accessToken: String,
    ) : Call<Void>
}