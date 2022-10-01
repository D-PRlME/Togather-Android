package com.tmdhoon.togather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmdhoon.togather.model.response.MainResponse
import com.tmdhoon.togather.model.response.TagResponse
import com.tmdhoon.togather.repository.MainRepository
import retrofit2.Response

class MainViewModel : ViewModel() {
    val tagResponse : MutableLiveData<Response<TagResponse>> = MutableLiveData()
    val mainResponse : MutableLiveData<Response<MainResponse>> = MutableLiveData()

    val mainRepository = MainRepository(this)

    fun tag(){
        mainRepository.tag()
    }

    fun get(){
        mainRepository.get()
    }
}