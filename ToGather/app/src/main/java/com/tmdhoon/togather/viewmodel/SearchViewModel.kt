package com.tmdhoon.togather.viewmodel

import androidx.lifecycle.MutableLiveData
import com.tmdhoon.togather.dto.response.PostListResponse
import com.tmdhoon.togather.repository.SearchRepository
import retrofit2.Response

class SearchViewModel {
    private val searchRepository : SearchRepository by lazy {
        SearchRepository(this)
    }

    val searchPostTitleResponse : MutableLiveData<Response<PostListResponse>> = MutableLiveData()

    fun searchPostTitle(title : String){
        searchRepository.searchPost(title)
    }
}