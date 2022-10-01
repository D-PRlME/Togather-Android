package com.tmdhoon.togather.model.request

import com.tmdhoon.togather.model.request.data.Tags

data class PostRequest(
    val title : String,
    val tags : ArrayList<Tags>,
    val content : String,
    val link : String,
)
