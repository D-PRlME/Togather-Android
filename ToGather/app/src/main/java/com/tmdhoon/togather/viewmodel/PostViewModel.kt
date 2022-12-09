package com.tmdhoon.togather.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tmdhoon.togather.dto.request.data.Tags
import com.tmdhoon.togather.repository.PostRepository
import retrofit2.Response

class PostViewModel : ViewModel() {

    val postResponse: MutableLiveData<Response<Void>> = MutableLiveData()
    val editResponse: MutableLiveData<Response<Void>> = MutableLiveData()

    private val postRepository by lazy {
        PostRepository(this)
    }

    fun post(
        title: String,
        content: String,
        tagList: ArrayList<String>
    ) {
        postRepository.post(
            title = title,
            tags = tagList,
            content = content,
        )
    }

    fun editPost(
        title: String,
        content: String,
        postId: Int,
        tagList: ArrayList<String>
    ) {
        postRepository.editPost(
            title = title,
            tags = tagList,
            content = content,
            postId = postId.toLong(),
        )
    }
}