package com.tmdhoon.togather.dto.response

import com.tmdhoon.togather.dto.response.data.Tags
import com.tmdhoon.togather.dto.response.data.User

data class MyPostsResponse(
    val post_list : ArrayList<MyPostList>,
)

data class MyPostList(
    val post_id : Int,
    val title : String,
    val user : User,
    val tags : ArrayList<Tags>,
    val created_at : String,
    val is_complete : Boolean,
    val like_count : Int,
)