package com.tmdhoon.togather.repository

import com.tmdhoon.togather.dto.request.AccountEditRequest
import com.tmdhoon.togather.dto.request.DeleteUserRequest
import com.tmdhoon.togather.dto.response.MyInfoResponse
import com.tmdhoon.togather.dto.response.MyPostsResponse
import com.tmdhoon.togather.network.ApiProvider
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.viewmodel.MyInfoViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyInfoRepository(
    private val myInfoViewModel: MyInfoViewModel,
    ) {

    fun myInfo(){
        ApiProvider.retrofit.myInfo(
            accessToken = "Bearer $ACCESS_TOKEN",
        ).enqueue(object : Callback<MyInfoResponse> {
            override fun onResponse(
                call: Call<MyInfoResponse>,
                response: Response<MyInfoResponse>,
            ) {
                myInfoViewModel.myInfoResponse.value = response
            }

            override fun onFailure(
                call: Call<MyInfoResponse>,
                t: Throwable,
            ) {

            }
        })
    }

    fun editAccount(
        name : String,
        url : String,
        introduce : String,
        position : ArrayList<String>,
    ){
        ApiProvider.retrofit.editAccount(
            accessToken = "Bearer $ACCESS_TOKEN",
            accountEditRequest = AccountEditRequest(
                name = name,
                profile_image_url = "https://avatars.githubusercontent.com/u/102812085?v=4",
                introduce = introduce,
                positions = position,
            )).enqueue(object : Callback<Void>{
            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>,
            ) {
                myInfoViewModel.accountEditResponse.value = response
            }

            override fun onFailure(
                call: Call<Void>,
                t: Throwable,
            ) {
            }
        })
    }

    fun deleteUser(password : String){
        ApiProvider.retrofit.deleteUser(
            accessToken = "Bearer $ACCESS_TOKEN",
            deleteUserRequest = DeleteUserRequest(password))
            .enqueue(object : Callback<Void>{
            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>,
            ) {
                myInfoViewModel.deleteUserResponse.value = response
            }

            override fun onFailure(
                call: Call<Void>,
                t: Throwable,
            ) {
            }
        })
    }

    fun getMyPostList(){
        ApiProvider.retrofit.getMyPostList(
            accessToken = "Bearer $ACCESS_TOKEN",
        ).enqueue(object : Callback<MyPostsResponse>{
            override fun onResponse(
                call: Call<MyPostsResponse>,
                response: Response<MyPostsResponse>,
            ) {
                myInfoViewModel.myPostsResponse.value = response
            }

            override fun onFailure(
                call: Call<MyPostsResponse>,
                t: Throwable,
            ) {
            }
        })
    }
}