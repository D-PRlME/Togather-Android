package com.tmdhoon.togather.dto.response.data


data class PostList(
    val post_id : Int,
    val title : String,
    val user : User,
    val tags : ArrayList<Tags>,
    val created_at : String,
    val is_complete : Boolean,
    val like_count : Int,
)
