package com.tmdhoon.togather.repository

import com.tmdhoon.togather.dto.request.EmailRequest
import com.tmdhoon.togather.network.ApiProvider
import com.tmdhoon.togather.viewmodel.ChangePwViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePwRepository(private val changePwViewModel: ChangePwViewModel) {
    fun changePwVerifyEmail(email : String){
        val emailRequest = EmailRequest(email)
        ApiProvider.retrofit.changePwVerifyEmail(emailRequest).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                changePwViewModel.changePwVerifyEmailResponse.value = response
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

            }
        })
    }
}