package com.tmdhoon.togather.repository

import com.tmdhoon.togather.dto.response.PostListResponse
import com.tmdhoon.togather.network.searchApi
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.viewmodel.SearchViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepository(
    private val searchViewModel : SearchViewModel,
) {
    fun searchPost(title : String){
       searchApi.searchPostTitle(
           accessToken = "Bearer $ACCESS_TOKEN",
           title = title,
       ).enqueue(object : Callback<PostListResponse>{
           override fun onResponse(
               call: Call<PostListResponse>,
               response: Response<PostListResponse>,
           ) {
               searchViewModel.searchPostTitleResponse.value = response
           }

           override fun onFailure(
               call: Call<PostListResponse>,
               t: Throwable,
           ) {
           }
       })
    }

    fun searchPostTag(tag : String){
        searchApi.searchPostTag(
            accessToken = "Bearer $ACCESS_TOKEN",
            tag = tag,
        ).enqueue(object : Callback<PostListResponse>{
            override fun onResponse(
                call: Call<PostListResponse>,
                response: Response<PostListResponse>
            ) {
                searchViewModel.searchPostTitleResponse.value = response
            }

            override fun onFailure(call: Call<PostListResponse>, t: Throwable) {

            }
        })
    }
}