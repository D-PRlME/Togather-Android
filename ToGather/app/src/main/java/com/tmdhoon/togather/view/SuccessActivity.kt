package com.tmdhoon.togather.view   

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ActivitySuccessBinding
import com.tmdhoon.togather.util.startIntent

class SuccessActivity : AppCompatActivity() {

    private val binding : ActivitySuccessBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_success)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNextButton()
    }

    private fun initNextButton() {
        binding.btSuccessNext.setOnClickListener {
            startIntent(this, MainActivity::class.java)
        }
    }
}