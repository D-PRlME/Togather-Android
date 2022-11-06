package com.tmdhoon.togather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmdhoon.togather.dto.response.MyInfoResponse
import com.tmdhoon.togather.repository.MyInfoRepository
import com.tmdhoon.togather.util.startIntent
import com.tmdhoon.togather.view.AuthChangePwActivity
import com.tmdhoon.togather.view.fragment.MyInfoFragment
import retrofit2.Response

class MyInfoViewModel : ViewModel() {
    val myInfoResponse: MutableLiveData<Response<MyInfoResponse>> = MutableLiveData()
    val accountEditResponse : MutableLiveData<Response<Void>> = MutableLiveData()
    val deleteUserResponse : MutableLiveData<Response<Void>> = MutableLiveData()

    private val myInfoRepository: MyInfoRepository by lazy {
        MyInfoRepository(this)
    }

    fun myInfo() {
        myInfoRepository.myInfo()
    }

    fun editAccount(name : String, url : String, introduce : String, positon : List<String>){
        myInfoRepository.editAccount(name, url, introduce, positon)
    }

    fun deleteUser(password : String){
        myInfoRepository.deleteUser(password)
    }
}
