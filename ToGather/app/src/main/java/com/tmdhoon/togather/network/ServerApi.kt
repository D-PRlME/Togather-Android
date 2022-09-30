package com.tmdhoon.togather.network

import com.tmdhoon.togather.model.data.PostList
import com.tmdhoon.togather.model.request.LoginRequest
import com.tmdhoon.togather.model.response.LoginResponse
import com.tmdhoon.togather.model.response.MainResponse
import com.tmdhoon.togather.model.response.TagResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
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
    ) : Call<PostList>
}