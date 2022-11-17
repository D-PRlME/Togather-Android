package com.tmdhoon.togather.repository

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

    fun getPosts(postId : Long) =
        ApiProvider.retrofit.getPosts("Bearer $ACCESS_TOKEN", postId).enqueue(object : Callback<DetailResponse>{
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                detailViewModel.detailResponse.value = response
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {

            }

        })


}