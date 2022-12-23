package com.tmdhoon.togather.network

import com.tmdhoon.togather.dto.request.CodeRequest
import com.tmdhoon.togather.dto.request.EmailRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface VerifyApi {

    // 이메일 중복 확인
    @POST("users/mail/duplicate")
    fun duplicate(
        @Body emailRequest: EmailRequest,
    ): Call<Void>

    // 인증 메일 전송
    @POST("users/mail/signup")
    fun verifyEmail(
        @Body emailRequest: EmailRequest,
    ): Call<Void>

    // 인증 번호 확인
    @POST("users/mail/verify")
    fun verifyCode(
        @Body codeRequest: CodeRequest,
    ): Call<Void>
}