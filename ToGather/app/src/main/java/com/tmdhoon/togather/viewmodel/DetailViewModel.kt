package com.tmdhoon.togather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmdhoon.togather.dto.response.DetailResponse
import com.tmdhoon.togather.repository.DetailRepository
import retrofit2.Response

class DetailViewModel() : ViewModel() {

    private val detailRepository : DetailRepository by lazy {
        DetailRepository(this)
    }

    val detailResponse : MutableLiveData<Response<DetailResponse>> = MutableLiveData()

    fun getPosts(postId : Int){
        detailRepository.getPosts(postId.toLong())
    }
}