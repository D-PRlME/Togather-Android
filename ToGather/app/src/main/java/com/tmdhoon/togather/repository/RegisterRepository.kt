package com.tmdhoon.togather.repository

import com.tmdhoon.togather.dto.request.CodeRequest
import com.tmdhoon.togather.dto.request.EmailRequest
import com.tmdhoon.togather.dto.response.LoginResponse
import com.tmdhoon.togather.dto.response.RegisterRequest
import com.tmdhoon.togather.network.registerApi
import com.tmdhoon.togather.network.verifyApi
import com.tmdhoon.togather.viewmodel.RegisterViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterRepository(private val registerViewModel: RegisterViewModel) {

    fun duplicate(email : String){
        val emailRequest = EmailRequest(email)
        verifyApi.duplicate(emailRequest).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                registerViewModel.duplicateResponse.value = response
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
            }
        })
    }

    fun verifyEmail(email : String){
        val emailRequest = EmailRequest(email)
        verifyApi.verifyEmail(emailRequest).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                registerViewModel.verifyEmailResponse.value = response

            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

            }
        })
    }

    fun verifyCode(email : String, code : String){
        val codeRequest = CodeRequest(email, code)
        verifyApi.verifyCode(codeRequest).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                registerViewModel.verifyCodeResponse.value = response
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

            }
        })
    }

    fun register(pw : String, name : String, email : String){
        val registerRequest = RegisterRequest(email, pw, name)
        registerApi.register(registerRequest).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                registerViewModel.registerResponse.value = response
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

            }
        })
    }
}