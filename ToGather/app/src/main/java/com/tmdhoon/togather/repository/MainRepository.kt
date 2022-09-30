package com.tmdhoon.togather.repository

import android.util.Log
import com.tmdhoon.togather.model.data.PostList
import com.tmdhoon.togather.model.response.MainResponse
import com.tmdhoon.togather.network.ApiProvider
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository(private val mainViewModel: MainViewModel) {

    fun get(){
        ApiProvider.retrofit.get("Bearer $ACCESS_TOKEN").enqueue(object : Callback<PostList>{
            override fun onResponse(
                call: Call<PostList>,
                response: Response<PostList>
            ) {
                mainViewModel.mainResponse.value = response
                Log.d("TEST", "${response.body()!!.title.get(0)}")
                Log.d("TEST", "onResponse : ${response.code()}")
            }

            override fun onFailure(call: Call<PostList>, t: Throwable) {
                Log.d("TEST", "onFailure : $t")
            }
        })
    }
}