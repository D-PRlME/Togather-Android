package com.tmdhoon.togather.network

import com.tmdhoon.togather.dto.request.CreateRoomRequest
import com.tmdhoon.togather.dto.response.ChattingResponse
import com.tmdhoon.togather.dto.response.CreateRoomResponse
import com.tmdhoon.togather.dto.response.RoomListResponse
import retrofit2.Call
import retrofit2.http.*

interface ChatApi {

    // 채팅방 생성
    @POST("chats/room")
    fun createRoom(
        @Header("Authorization") accessToken: String,
        @Body createRoomRequest: CreateRoomRequest,
    ) : Call<CreateRoomResponse>

    // 채팅 조회
    @GET("chats/{room-id}")
    fun getChat(
        @Header("Authorization") accessToken: String,
        @Path("room-id") roomId : Long,
        @Query("time") time : String,
    ) : Call<ChattingResponse>

    // 채팅 방 목록 조회
    @GET("chats/room")
    fun getRoomList(
        @Header("Authorization") accessToken: String,
    ) : Call<RoomListResponse>
}