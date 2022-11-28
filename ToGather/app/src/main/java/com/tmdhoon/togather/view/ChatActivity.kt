package com.tmdhoon.togather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tmdhoon.togather.R
import com.tmdhoon.togather.base.BaseActivity
import com.tmdhoon.togather.databinding.ActivityChatBinding
import com.tmdhoon.togather.util.getPref
import com.tmdhoon.togather.util.initPref

class ChatActivity : BaseActivity<ActivityChatBinding>(R.layout.activity_chat) {

    private val pref by lazy {
        initPref(
            context = this,
            mode = MODE_PRIVATE,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNameTextView()
    }

    private fun initNameTextView(){
        binding.tvChatName.text = getPref(
            preferences = pref,
            key = "userName",
            value = "",
        ).toString()
    }
}