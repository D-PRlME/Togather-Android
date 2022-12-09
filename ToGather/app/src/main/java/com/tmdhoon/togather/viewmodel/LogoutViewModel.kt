package com.tmdhoon.togather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmdhoon.togather.repository.LogoutRepository
import retrofit2.Response

class LogoutViewModel : ViewModel() {

    private val logoutRepository : LogoutRepository by lazy {
        LogoutRepository(this)
    }

    val logoutResponse : MutableLiveData<Response<Void>> = MutableLiveData()

    fun logout(){
        logoutRepository.logout()
    }
}