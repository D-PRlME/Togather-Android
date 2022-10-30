package com.tmdhoon.togather.repository

import com.tmdhoon.togather.network.ApiProvider
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.viewmodel.LogoutViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogoutRepository(private val logoutViewModel : LogoutViewModel) {
    fun logout(){
        ApiProvider.retrofit.logout("Bearer $ACCESS_TOKEN").enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                logoutViewModel.logoutResponse.value = response
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

            }

        })
    }
}