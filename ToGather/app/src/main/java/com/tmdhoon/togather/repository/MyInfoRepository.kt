package com.tmdhoon.togather.repository

import android.util.Log
import com.tmdhoon.togather.model.response.MyInfoResponse
import com.tmdhoon.togather.network.ApiProvider
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.viewmodel.MyInfoViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyInfoRepository(private val myInfoViewModel: MyInfoViewModel) {

    fun myInfo(){
        ApiProvider.retrofit.myInfo("Bearer $ACCESS_TOKEN").enqueue(object : Callback<MyInfoResponse> {
            override fun onResponse(
                call: Call<MyInfoResponse>,
                response: Response<MyInfoResponse>
            ) {
                myInfoViewModel.myInfoResponse.value = response
            }

            override fun onFailure(call: Call<MyInfoResponse>, t: Throwable) {

            }

        })
    }

}