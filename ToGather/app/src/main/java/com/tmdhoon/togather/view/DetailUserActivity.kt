package com.tmdhoon.togather.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.tmdhoon.togather.R
import com.tmdhoon.togather.base.BaseActivity
import com.tmdhoon.togather.databinding.ActivityDetailUserBinding
import com.tmdhoon.togather.util.startIntent
import com.tmdhoon.togather.viewmodel.MainViewModel

class DetailUserActivity : BaseActivity<ActivityDetailUserBinding>(R.layout.activity_detail_user) {

    private val mainViewModel : MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBackButton()
        fetchUserInfo()
        binding.mainViewModel = mainViewModel
    }

    private fun fetchUserInfo() {
        mainViewModel.userInfo(intent.getLongExtra("userId", 0).toInt())
    }

    private fun initBackButton() {
        binding.imgDetailUserBack.setOnClickListener {
            finish()
        }
    }
}