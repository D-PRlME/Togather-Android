package com.tmdhoon.togather.repository

import android.util.Log
import com.tmdhoon.togather.dto.response.PostListResponse
import com.tmdhoon.togather.dto.response.TagResponse
import com.tmdhoon.togather.dto.response.UserInfoResponse
import com.tmdhoon.togather.network.ApiProvider
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository(private val mainViewModel: MainViewModel) {

    fun get(){
        ApiProvider.retrofit.get("Bearer $ACCESS_TOKEN").enqueue(object : Callback<PostListResponse>{
            override fun onResponse(
                call: Call<PostListResponse>,
                response: Response<PostListResponse>
            ) {
                mainViewModel.postListResponse.value = response
            }

            override fun onFailure(call: Call<PostListResponse>, t: Throwable) {
            }
        })
    }

    fun tag(){
        ApiProvider.retrofit.tag("Bearer $ACCESS_TOKEN").enqueue(object : Callback<TagResponse> {
            override fun onResponse(call: Call<TagResponse>, response: Response<TagResponse>) {
                mainViewModel.tagResponse.value = response
            }
            override fun onFailure(call: Call<TagResponse>, t: Throwable) {
            }

        })
    }

    fun userInfo(userId : Int){
        ApiProvider.retrofit.userInfo("Bearer $ACCESS_TOKEN", userId).enqueue(object : Callback<UserInfoResponse>{
            override fun onResponse(
                call: Call<UserInfoResponse>,
                response: Response<UserInfoResponse>
            ) {
                mainViewModel.userInfoResponse.value = response
            }

            override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
            }
        })
    }

}