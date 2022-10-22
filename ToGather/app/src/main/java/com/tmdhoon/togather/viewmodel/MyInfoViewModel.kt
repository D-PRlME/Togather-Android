package com.tmdhoon.togather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmdhoon.togather.dto.response.MyInfoResponse
import com.tmdhoon.togather.repository.MyInfoRepository
import retrofit2.Response

class MyInfoViewModel : ViewModel() {
    val myInfoResponse: MutableLiveData<Response<MyInfoResponse>> = MutableLiveData()
    val accountEditResponse : MutableLiveData<Response<Void>> = MutableLiveData()

    private val myInfoRepository: MyInfoRepository by lazy {
        MyInfoRepository(this)
    }

    fun myInfo() {
        myInfoRepository.myInfo()
    }

    fun editAccount(name : String, url : String, introduce : String, positon : List<String>){
        myInfoRepository.editAccount(name, url, introduce, positon)
    }

}
