package com.tmdhoon.togather.network

import com.tmdhoon.togather.model.request.*
import com.tmdhoon.togather.model.response.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ServerApi {

    @POST("users/auth")
    fun login(
        @Body loginRequest: LoginRequest
    ) : Call<LoginResponse>

    @GET("posts/tag/list")
    fun tag(
        @Header("Authorization") accessToken : String
    ) : Call<TagResponse>

    @GET("posts")
    fun get(
        @Header("Authorization") accessToken: String
    ) : Call<MainResponse>

    @POST("posts")
    fun post(
        @Header("Authorization") accessToken: String,
        @Body postRequest: PostRequest
    ) : Call<Void>

    @GET("users")
    fun myInfo(
        @Header("Authorization") accessToken: String
    ) : Call<MyInfoResponse>

    @POST("users/mail/duplicate")
    fun duplicate(
        @Body emailRequest: EmailRequest
    ) : Call<Void>

    @POST("users/mail/signup")
    fun verifyEmail(
        @Body emailRequest: EmailRequest
    ) : Call<Void>

    @POST("users/mail/verify")
    fun verifyCode(
        @Body codeRequest: CodeRequest
    ) : Call<Void>

    @POST("users")
    fun register(
        @Body registerRequest: RegisterRequest
    ) : Call<LoginResponse>

    @PATCH("users")
    fun editAccount(
        @Header("Authorization") accessToken : String,
        @Body accountEditRequest: AccountEditRequest,
    ) : Call<Void>

    @POST("users/mail/find")
    fun changePwVerifyEmail(
         @Body emailRequest: EmailRequest
    ) : Call<Void>
}