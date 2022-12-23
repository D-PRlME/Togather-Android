package com.tmdhoon.togather.network

import com.tmdhoon.togather.dto.request.DeleteUserRequest
import com.tmdhoon.togather.dto.response.LoginResponse
import com.tmdhoon.togather.dto.response.RegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.POST

interface RegisterApi {

    // 회원가입
    @POST("users")
    fun register(
        @Body registerRequest: RegisterRequest,
    ): Call<LoginResponse>

    // 회원탈퇴
    @HTTP(method="DELETE", hasBody=true, path="users")
    fun deleteUser(
        @Header("Authorization") accessToken: String,
        @Body deleteUserRequest: DeleteUserRequest,
    ) : Call<Void>
}