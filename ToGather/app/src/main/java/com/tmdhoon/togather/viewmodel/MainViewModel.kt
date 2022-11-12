package com.tmdhoon.togather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmdhoon.togather.dto.response.MainResponse
import com.tmdhoon.togather.dto.response.TagResponse
import com.tmdhoon.togather.dto.response.UserInfoResponse
import com.tmdhoon.togather.repository.MainRepository
import retrofit2.Response

class MainViewModel : ViewModel() {
    val tagResponse : MutableLiveData<Response<TagResponse>> = MutableLiveData()
    val mainResponse : MutableLiveData<Response<MainResponse>> = MutableLiveData()
    val userInfoResponse : MutableLiveData<Response<UserInfoResponse>> = MutableLiveData()

    private val mainRepository : MainRepository by lazy{
        MainRepository(this)
    }

    fun tag(){
        mainRepository.tag()
    }

    fun get(){
        mainRepository.get()
    }

    fun userInfo(userId : Int){
        mainRepository.userInfo(userId)
    }
}