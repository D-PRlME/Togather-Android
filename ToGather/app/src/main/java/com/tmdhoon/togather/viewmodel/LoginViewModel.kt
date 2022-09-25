package com.tmdhoon.togather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmdhoon.togather.model.response.LoginResponse
import com.tmdhoon.togather.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel() : ViewModel() {
    val loginResponse: MutableLiveData<Response<LoginResponse>> = MutableLiveData()
    val repository : Repository = Repository(this)

    fun login(email: String, pw: String) {
        repository.login(email, pw)
    }


}