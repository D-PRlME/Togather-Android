package com.tmdhoon.togather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmdhoon.togather.model.response.LoginResponse
import com.tmdhoon.togather.repository.RegisterRepository
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    val duplicateResponse : MutableLiveData<Response<Void>> = MutableLiveData()
    val verifyEmailResponse : MutableLiveData<Response<Void>> = MutableLiveData()
    val verifyCodeResponse : MutableLiveData<Response<Void>> = MutableLiveData()
    val registerResponse : MutableLiveData<Response<LoginResponse>> = MutableLiveData()

    private val registerRepository : RegisterRepository by lazy {
        RegisterRepository(this)
    }

    fun duplicate(email : String){
        registerRepository.duplicate(email)
    }

    fun verifyEmail(email : String){
        registerRepository.verifyEmail(email)
    }

    fun verifyCode(email : String, code : Int){
        registerRepository.verifyCode(email, code)
    }

    fun register(email : String, pw : String, name : String){
        registerRepository.register(email, pw, name)
    }

}