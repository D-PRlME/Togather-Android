package com.tmdhoon.togather.repository

import android.util.Log
import com.tmdhoon.togather.dto.response.DetailResponse
import com.tmdhoon.togather.network.ApiProvider
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.viewmodel.DetailViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRepository(
    private val detailViewModel: DetailViewModel,
) {

    fun getPosts(postId: Long) =
        ApiProvider.retrofit.getPosts(
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
        ApiProvider.retrofit.like(
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
        ApiProvider.retrofit.unLike(
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
        ApiProvider.retrofit.deletePost(
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
}