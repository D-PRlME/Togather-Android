package com.tmdhoon.togather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmdhoon.togather.model.request.data.Tags
import com.tmdhoon.togather.repository.PostRepository
import retrofit2.Response

class PostViewModel : ViewModel() {

    val postResponse : MutableLiveData<Response<Void>> = MutableLiveData()

    private val postRepository by lazy {
        PostRepository(this)
    }

    fun post(title : String, tags : ArrayList<Tags>, content : String, link : String){
        postRepository.post(title, tags, content, link)
    }


}