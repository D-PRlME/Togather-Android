package com.tmdhoon.togather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmdhoon.togather.repository.ChangePwRepository
import retrofit2.Response

class ChangePwViewModel:ViewModel() {
    val changePwVerifyEmailResponse : MutableLiveData<Response<Void>> = MutableLiveData()
    private val changePwRepository : ChangePwRepository by lazy {
        ChangePwRepository(this)
    }

    fun changePwVerifyEmail(email : String){
        changePwRepository.changePwVerifyEmail(email)
    }



}