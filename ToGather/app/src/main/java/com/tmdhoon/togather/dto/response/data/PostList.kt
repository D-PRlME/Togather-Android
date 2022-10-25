package com.tmdhoon.togather.dto.response.data


data class PostList(
    val post_id : Int,
    val title : String,
    val user : User,
    val created_at : String,
    val tags : ArrayList<TagLists>,
    val is_finished : Boolean,
    val like_count : Int,
)
