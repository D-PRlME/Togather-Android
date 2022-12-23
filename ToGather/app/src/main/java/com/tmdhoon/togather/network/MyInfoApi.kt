package com.tmdhoon.togather.network

import com.tmdhoon.togather.dto.request.AccountEditRequest
import com.tmdhoon.togather.dto.request.EmailRequest
import com.tmdhoon.togather.dto.request.NewPwRequest
import com.tmdhoon.togather.dto.response.MyInfoResponse
import retrofit2.Call
import retrofit2.http.*

interface MyInfoApi {

    // 내 정보
    @GET("users")
    fun myInfo(
        @Header("Authorization") accessToken: String,
    ): Call<MyInfoResponse>

    // 계정 정보 수정
    @PATCH("users")
    fun editAccount(
        @Header("Authorization") accessToken: String,
        @Body accountEditRequest: AccountEditRequest,
    ): Call<Void>

    // 비밀번호 변경 용 인증 메일 전송
    @POST("users/mail")
    fun changePwVerifyEmail(
        @Header("Authorization") accessToken: String,
        @Body emailRequest: EmailRequest,
    ): Call<Void>

    // 새 비밀번호
    @PUT("users/password")
    fun newPassword(
        @Header("Authorization") accessToken: String,
        @Body newPwRequest: NewPwRequest,
    ) : Call<Void>
}