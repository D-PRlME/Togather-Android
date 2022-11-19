package com.tmdhoon.togather.network

import com.tmdhoon.togather.dto.request.*
import com.tmdhoon.togather.dto.response.*
import retrofit2.Call
import retrofit2.http.*

interface ServerApi {

    // 회원가입
    @POST("users/auth")
    fun login(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>

    // 메인 태그 리스트 조회
    @GET("posts/tag/list")
    fun tag(
        @Header("Authorization") accessToken: String
    ): Call<TagResponse>

    // 글 목록 조회
    @GET("posts")
    fun get(
        @Header("Authorization") accessToken: String
    ): Call<MainResponse>

    // 글쓰기
    @POST("posts")
    fun post(
        @Header("Authorization") accessToken: String,
        @Body postRequest: PostRequest
    ): Call<Void>

    // 내 정보
    @GET("users")
    fun myInfo(
        @Header("Authorization") accessToken: String
    ): Call<MyInfoResponse>

    // 이메일 중복 확인
    @POST("users/mail/duplicate")
    fun duplicate(
        @Body emailRequest: EmailRequest
    ): Call<Void>

    // 인증 메일 전송
    @POST("users/mail/signup")
    fun verifyEmail(
        @Body emailRequest: EmailRequest
    ): Call<Void>

    // 인증 번호 확인
    @POST("users/mail/verify")
    fun verifyCode(
        @Body codeRequest: CodeRequest
    ): Call<Void>

    // 회원가입
    @POST("users")
    fun register(
        @Body registerRequest: RegisterRequest
    ): Call<LoginResponse>

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
        @Body emailRequest: EmailRequest
    ): Call<Void>

    // 새 비밀번호
    @PUT("users/password")
    fun newPassword(
        @Header("Authorization") accessToken: String,
        @Body newPwRequest: NewPwRequest
    ) : Call<Void>

    // 로그아웃
    @DELETE("users/logout")
    fun logout(
        @Header("Authorization") accessToken: String,
    ) : Call<Void>

    // 회원탈퇴
    @HTTP(method="DELETE", hasBody=true, path="users")
    fun deleteUser(
        @Header("Authorization") accessToken: String,
        @Body deleteUserRequest: DeleteUserRequest,
    ) : Call<Void>

    @GET("users/{user-id}")
    fun userInfo(
        @Header("Authorization") accessToken: String,
        @Path("user-id") userId : Int,
    ) : Call<UserInfoResponse>

    // 게시글 상세조회
    @GET("posts/{post-id}")
    fun getPosts(
        @Header("Authorization") accessToken: String,
        @Path("post-id") postId : Long,
    ) : Call<DetailResponse>

    // 좋아요 추가
    @POST("posts/like/{post-id}")
    fun like(
        @Header("Authorization") accessToken: String,
        @Path("post-id") postId : Long,
    ) : Call<Void>

    // 좋아요 취소
    @DELETE("posts/like/{post-id}")
    fun unLike(
        @Header("Authorization") accessToken : String,
        @Path("post-id") postId : Long,
    ) : Call<Void>

    @GET("posts/my")
    fun getMyPostsList(
        @Header("Authorization") accessToken: String,
    ) : Call<MyPostsResponse>

}