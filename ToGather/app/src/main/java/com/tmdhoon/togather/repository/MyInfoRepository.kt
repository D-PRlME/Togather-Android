package com.tmdhoon.togather.repository

import android.util.Log
import com.tmdhoon.togather.model.request.AccountEditRequest
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
                Log.d("TEST", response.code().toString())
            }

            override fun onFailure(call: Call<MyInfoResponse>, t: Throwable) {

            }

        })
    }

    fun editAccount(name : String, url : String, introduce : String, position : List<String>){
        val accountEditRequest = AccountEditRequest(name, url, introduce, position)
        Log.d("TEST", "name : $name")
        Log.d("TEST", "url : $url")
        Log.d("TEST", "introduce : $introduce")
        Log.d("TEST", "position : $position")
        ApiProvider.retrofit.editAccount("Bearer $ACCESS_TOKEN", accountEditRequest).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                myInfoViewModel.accountEditResponse.value = response
                Log.d("TEST", response.errorBody()?.string()!!)
                Log.d("TEST", response.code().toString())
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

            }

        })
    }

}