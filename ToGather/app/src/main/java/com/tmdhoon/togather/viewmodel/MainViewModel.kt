package com.tmdhoon.togather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmdhoon.togather.model.response.TagResponse
import com.tmdhoon.togather.repository.TagRepository
import retrofit2.Response

class MainViewModel : ViewModel() {
    val tagResponse : MutableLiveData<Response<TagResponse>> = MutableLiveData()
    val tagRepository = TagRepository(this)

    fun tag(accessToken : String){
        tagRepository.tag(accessToken)
    }

}