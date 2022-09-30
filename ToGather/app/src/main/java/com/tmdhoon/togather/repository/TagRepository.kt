package com.tmdhoon.togather.repository

import android.util.Log
import com.tmdhoon.togather.model.response.TagResponse
import com.tmdhoon.togather.network.ApiProvider
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.util.Bearer
import com.tmdhoon.togather.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TagRepository(private val mainViewModel: MainViewModel) {
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