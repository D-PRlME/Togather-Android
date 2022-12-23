package com.tmdhoon.togather.network

import com.tmdhoon.togather.dto.request.PostRequest
import com.tmdhoon.togather.dto.response.MyPostsResponse
import retrofit2.Call
import retrofit2.http.*

interface MyPostApi {

    // 내 게시글 조회
    @GET("posts/my")
    fun getMyPostList(
        @Header("Authorization") accessToken: String,
    ) : Call<MyPostsResponse>

    // 내 게시글 수정
    @PATCH("posts/{post-id}")
    fun editPost(
        @Header("Authorization") accessToken: String,
        @Path("post-id") postId : Long,
        @Body postRequest : PostRequest,
    ) : Call<Void>

    // 내 게시글 삭제
    @DELETE("posts/{post-id}")
    fun deletePost(
        @Header("Authorization") accessToken: String,
        @Path("post-id") postId : Long,
    ) : Call<Void>
}