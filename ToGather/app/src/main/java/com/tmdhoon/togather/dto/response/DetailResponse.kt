package com.tmdhoon.togather.dto.response

import com.tmdhoon.togather.dto.response.data.Tags
import com.tmdhoon.togather.dto.response.data.User

data class DetailResponse(
    val post_id : Long,
    val title : String,
    val user : User,
    val is_mine : Boolean,
    val created_at : String,
    val tag : ArrayList<Tags>,
    val content : String,
    val is_completed : Boolean,
    val is_liked : Long,
    val like_count : Long,
)
