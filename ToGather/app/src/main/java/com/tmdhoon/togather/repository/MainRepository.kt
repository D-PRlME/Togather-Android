package com.tmdhoon.togather.repository

import android.util.Log
import com.tmdhoon.togather.model.response.MainResponse
import com.tmdhoon.togather.model.response.TagResponse
import com.tmdhoon.togather.network.ApiProvider
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository(private val mainViewModel: MainViewModel) {

    fun get(){
        ApiProvider.retrofit.get("Bearer $ACCESS_TOKEN").enqueue(object : Callback<MainResponse>{
            override fun onResponse(
                call: Call<MainResponse>,
                response: Response<MainResponse>
            ) {
                mainViewModel.mainResponse.value = response
            }

            override fun onFailure(call: Call<MainResponse>, t: Throwable) {
            }
        })
    }

    fun tag(){
        ApiProvider.retrofit.tag("Bearer ${ACCESS_TOKEN}").enqueue(object : Callback<TagResponse> {
            override fun onResponse(call: Call<TagResponse>, response: Response<TagResponse>) {
                mainViewModel.tagResponse.value = response
            }
            override fun onFailure(call: Call<TagResponse>, t: Throwable) {
            }

        })
    }

}