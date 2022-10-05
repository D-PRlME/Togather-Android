package com.tmdhoon.togather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmdhoon.togather.model.response.LoginResponse
import com.tmdhoon.togather.repository.LoginRepository
import retrofit2.Response

class LoginViewModel() : ViewModel() {
    val loginResponse: MutableLiveData<Response<LoginResponse>> = MutableLiveData()

    private val loginRepository : LoginRepository by lazy{
        LoginRepository(this)
    }

    fun login(email: String, pw: String) {
        loginRepository.login(email, pw)
    }
}