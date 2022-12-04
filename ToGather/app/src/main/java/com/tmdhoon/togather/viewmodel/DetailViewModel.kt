package com.tmdhoon.togather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmdhoon.togather.dto.request.CreateRoomRequest
import com.tmdhoon.togather.dto.response.CreateRoomResponse
import com.tmdhoon.togather.dto.response.DetailResponse
import com.tmdhoon.togather.repository.DetailRepository
import com.tmdhoon.togather.util.initPref
import com.tmdhoon.togather.util.putPref
import retrofit2.Response

class DetailViewModel() : ViewModel() {

    private val detailRepository : DetailRepository by lazy {
        DetailRepository(this)
    }

    val detailResponse : MutableLiveData<Response<DetailResponse>> = MutableLiveData()
    val likeOnResponse : MutableLiveData<Response<Void>> = MutableLiveData()
    val likeOffResponse : MutableLiveData<Response<Void>> = MutableLiveData()
    val deleteResponse : MutableLiveData<Response<Void>> = MutableLiveData()
    val createRoomResponse : MutableLiveData<Response<CreateRoomResponse>> = MutableLiveData()

    fun getPosts(postId : Int){
        detailRepository.getPosts(postId.toLong())
    }

    fun like(postId: Int){
        detailRepository.like(postId.toLong())
    }

    fun unLike(postId : Int){
        detailRepository.unLike(postId.toLong())
    }

    fun deletePost(postId : Int){
        detailRepository.deletePost(postId.toLong())
    }

    fun createRoom(userId : Int){
        detailRepository.createRoom(
            CreateRoomRequest(userId.toLong())
        )
    }
}