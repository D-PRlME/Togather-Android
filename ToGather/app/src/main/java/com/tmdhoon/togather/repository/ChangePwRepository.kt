package com.tmdhoon.togather.repository

import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import com.tmdhoon.togather.dto.request.EmailRequest
import com.tmdhoon.togather.network.ApiProvider
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.util.TAG
import com.tmdhoon.togather.viewmodel.ChangePwViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePwRepository(private val changePwViewModel: ChangePwViewModel) {
    fun changePwVerifyEmail(email : String){
        val emailRequest = EmailRequest(email)
        ApiProvider.retrofit.changePwVerifyEmail("Bearer $ACCESS_TOKEN", emailRequest).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                changePwViewModel.changePwVerifyEmailResponse.value = response
                Log.d(TAG, response.code().toString())
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

            }
        })
    }
}