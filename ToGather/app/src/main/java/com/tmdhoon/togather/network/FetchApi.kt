package com.tmdhoon.togather.network

import com.tmdhoon.togather.dto.response.PostListResponse
import com.tmdhoon.togather.dto.response.TagResponse
import com.tmdhoon.togather.dto.response.UserInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface FetchApi {

    // 메인 태그 리스트 조회
    @GET("posts/tag/list")
    fun tag(
        @Header("Authorization") accessToken: String,
    ): Call<TagResponse>

    // 글 목록 조회
    @GET("posts")
    fun get(
        @Header("Authorization") accessToken: String,
    ): Call<PostListResponse>

    // 유저 정보 조회
    @GET("users/{user-id}")
    fun userInfo(
        @Header("Authorization") accessToken: String,
        @Path("user-id") userId : Int,
    ) : Call<UserInfoResponse>
}