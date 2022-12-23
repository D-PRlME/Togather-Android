package com.tmdhoon.togather.network

import com.tmdhoon.togather.dto.request.PostRequest
import com.tmdhoon.togather.dto.response.DetailResponse
import retrofit2.Call
import retrofit2.http.*

interface PostApi {

    // 글쓰기
    @POST("posts")
    fun post(
        @Header("Authorization") accessToken: String,
        @Body postRequest: PostRequest,
    ): Call<Void>

    // 게시글 상세조회
    @GET("posts/{post-id}")
    fun getPosts(
        @Header("Authorization") accessToken: String,
        @Path("post-id") postId : Long,
    ) : Call<DetailResponse>

    // 좋아요 추가
    @POST("posts/like/{post-id}")
    fun like(
        @Header("Authorization") accessToken: String,
        @Path("post-id") postId : Long,
    ) : Call<Void>

    // 좋아요 취소
    @DELETE("posts/like/{post-id}")
    fun unLike(
        @Header("Authorization") accessToken : String,
        @Path("post-id") postId : Long,
    ) : Call<Void>
}