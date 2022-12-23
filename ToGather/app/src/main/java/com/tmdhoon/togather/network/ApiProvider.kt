package com.tmdhoon.togather.network

import com.tmdhoon.togather.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private val retrofit = RetrofitClient.getInstance()

val loginApi = retrofit.create(LoginApi::class.java)

val registerApi = retrofit.create(RegisterApi::class.java)

val chatApi = retrofit.create(ChatApi::class.java)

val fetchApi = retrofit.create(FetchApi::class.java)

val myInfoApi = retrofit.create(MyInfoApi::class.java)

val postApi = retrofit.create(PostApi::class.java)

val searchApi = retrofit.create(SearchApi::class.java)

val verifyApi = retrofit.create(VerifyApi::class.java)

val myPostApi = retrofit.create(MyPostApi::class.java)