package com.tmdhoon.togather.dto.request

import com.tmdhoon.togather.dto.request.data.Tags

data class PostRequest(
    val title : String,
    val tags : ArrayList<Tags>,
    val content : String,
    val link : String,
)
