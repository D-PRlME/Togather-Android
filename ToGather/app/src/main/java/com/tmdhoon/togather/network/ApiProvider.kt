package com.tmdhoon.togather.network

import com.tmdhoon.togather.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private val retrofit = RetrofitClient.getInstance()

fun getLoginApi() : LoginApi = retrofit.create(LoginApi::class.java)

fun getRegisterApi() : RegisterApi = retrofit.create(RegisterApi::class.java)

fun getChatApi() : ChatApi = retrofit.create(ChatApi::class.java)

fun getFetchApi() : FetchApi = retrofit.create(FetchApi::class.java)

fun getMyInfoApi() : MyInfoApi = retrofit.create(MyInfoApi::class.java)

fun getPostApi() : PostApi = retrofit.create(PostApi::class.java)

fun getSearchApi() : SearchApi = retrofit.create(SearchApi::class.java)

fun getVerifyApi() : VerifyApi = retrofit.create(VerifyApi::class.java)
