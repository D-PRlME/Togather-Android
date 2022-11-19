package com.tmdhoon.togather.repository

import com.tmdhoon.togather.dto.request.PostRequest
import com.tmdhoon.togather.dto.request.data.Tags
import com.tmdhoon.togather.network.ApiProvider
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.viewmodel.PostViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepository(
    private val postViewModel : PostViewModel,
    ) {

    fun post(
        title : String,
        tags : ArrayList<Tags>,
        content : String,
    ){
        ApiProvider.retrofit.post(
            accessToken = "Bearer $ACCESS_TOKEN",
            postRequest = PostRequest(
                title = title,
                tags = tags,
                content = content,
            ),
        ).enqueue(object :
            Callback<Void> {
            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>,
            ) {
                postViewModel.postResponse.value = response
            }

            override fun onFailure(
                call: Call<Void>,
                t: Throwable,
            ) {
            }
        })
    }

    fun editPost(
        title : String,
        tags : ArrayList<Tags>,
        content : String,
        postId : Long,
    ){
        ApiProvider.retrofit.editPosts(
            accessToken = "Bearer $ACCESS_TOKEN",
            postId = postId,
            postRequest = PostRequest(
                title = title,
                tags = tags,
                content = content,
            ),
        ).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                postViewModel.editResponse.value = response
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

            }
        })
    }
}