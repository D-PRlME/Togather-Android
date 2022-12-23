package com.tmdhoon.togather.network

import com.tmdhoon.togather.dto.response.PostListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchApi {

    // 게시글 제목 검색
    @GET("posts/title")
    fun searchPostTitle(
        @Header("Authorization") accessToken: String,
        @Query("title") title : String,
    ) : Call<PostListResponse>

    // 게시글 태그 검색
    @GET("posts/tag")
    fun searchPostTag(
        @Header("Authorization") accessToken: String,
        @Query("tag") tag : String,
    ) : Call<PostListResponse>
}