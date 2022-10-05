package com.tmdhoon.togather.repository

import android.util.Log
import com.tmdhoon.togather.model.request.LoginRequest
import com.tmdhoon.togather.model.response.LoginResponse
import com.tmdhoon.togather.model.response.TagResponse
import com.tmdhoon.togather.network.ApiProvider
import com.tmdhoon.togather.viewmodel.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository(private val loginViewModel: LoginViewModel) {

    fun login(email : String, pw : String){
        val loginRequest = LoginRequest(email, pw)

        ApiProvider.retrofit.login(loginRequest).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                loginViewModel.loginResponse.value = response
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

            }
        })
    }
}