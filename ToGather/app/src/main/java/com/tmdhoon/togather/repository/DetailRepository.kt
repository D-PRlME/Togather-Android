package com.tmdhoon.togather.repository

import com.tmdhoon.togather.dto.request.CreateRoomRequest
import com.tmdhoon.togather.dto.response.CreateRoomResponse
import com.tmdhoon.togather.dto.response.DetailResponse
import com.tmdhoon.togather.network.chatApi
import com.tmdhoon.togather.network.myPostApi
import com.tmdhoon.togather.network.postApi
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.viewmodel.DetailViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRepository(
    private val detailViewModel: DetailViewModel,
) {

    fun getPosts(postId: Long) =
        postApi.getPosts(
            accessToken = "Bearer $ACCESS_TOKEN",
            postId = postId,
        )
            .enqueue(object : Callback<DetailResponse> {
                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    detailViewModel.detailResponse.value = response
                }

                override fun onFailure(
                    call: Call<DetailResponse>,
                    t: Throwable,
                ) {

                }
            })

    fun like(postId: Long) =
        postApi.like(
            accessToken = "Bearer $ACCESS_TOKEN",
            postId = postId,
        ).enqueue(object : Callback<Void> {
            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>,
            ) {
                detailViewModel.likeOnResponse.value = response
            }

            override fun onFailure(
                call: Call<Void>,
                t: Throwable,
            ) {
            }
        })

    fun unLike(postId: Long) =
        postApi.unLike(
            accessToken = "Bearer $ACCESS_TOKEN",
            postId = postId,
        )
            .enqueue(object : Callback<Void> {
                override fun onResponse(
                    call: Call<Void>,
                    response: Response<Void>,
                ) {
                    detailViewModel.likeOffResponse.value = response
                }

                override fun onFailure(
                    call: Call<Void>,
                    t: Throwable,
                ) {
                }
            })

    fun deletePost(postId: Long) =
        myPostApi.deletePost(
            accessToken = "Bearer $ACCESS_TOKEN",
            postId = postId,
        ).enqueue(object : Callback<Void>{
            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>,
            ) {
                detailViewModel.deleteResponse.value = response
            }

            override fun onFailure(
                call: Call<Void>,
                t: Throwable,
            ) {
            }
        })

    fun createRoom(createRoomRequest: CreateRoomRequest){
        chatApi.createRoom(
            accessToken = "Bearer $ACCESS_TOKEN",
            createRoomRequest = createRoomRequest,
        ).enqueue(object : Callback<CreateRoomResponse>{
            override fun onResponse(
                call: Call<CreateRoomResponse>,
                response: Response<CreateRoomResponse>,
            ) {
                detailViewModel.createRoomResponse.value = response
            }

            override fun onFailure(
                call: Call<CreateRoomResponse>,
                t: Throwable,
            ) {

            }
        })
    }
}