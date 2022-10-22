package com.tmdhoon.togather.dto.response

import com.tmdhoon.togather.dto.response.data.PostList

data class MainResponse(
    val post_list : ArrayList<PostList>,
)