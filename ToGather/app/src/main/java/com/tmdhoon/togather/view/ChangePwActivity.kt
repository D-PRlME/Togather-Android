package com.tmdhoon.togather.view

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tmdhoon.togather.R
import com.tmdhoon.togather.base.BaseActivity
import com.tmdhoon.togather.databinding.ActivityChangePwBinding
import com.tmdhoon.togather.util.printToast
import com.tmdhoon.togather.viewmodel.ChangePwViewModel

class ChangePwActivity : BaseActivity<ActivityChangePwBinding>(R.layout.activity_change_pw) {

    private val changePwViewModel: ChangePwViewModel by lazy {
        ViewModelProvider(this).get(ChangePwViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserveEmailEdittext()
        initNextButton()
        initObserveVerifyEmailResponse()
        binding.changePwActivity = this
        binding.lifecycleOwner = this
    }

    fun initNextButton() {
        val email = binding.etChangePwEmail.text.toString()
        if (email.isNotEmpty()) {
            changePwViewModel.changePwVerifyEmail(email)
        }
    }

    private fun initObserveVerifyEmailResponse() {
        changePwViewModel.changePwVerifyEmailResponse.observe(this, Observer {
            when (it.code()) {
                204 -> printToast(this, "인증 메일이 전송되었습니다!")
            }
        })
    }

    private fun checkNull() {
        val email = binding.etChangePwEmail.text
        if (email.isNotEmpty()) {
            binding.btChangePwNext.setBackgroundResource(R.drawable.button_yellow)
            binding.btChangePwNext.setTextColor(Color.BLACK)
        } else {
            binding.btChangePwNext.setBackgroundResource(R.drawable.button_white)
            binding.btChangePwNext.setTextColor(getColor(R.color.all_background_focusOut))
        }
    }

    private fun initObserveEmailEdittext() {
        binding.etChangePwEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                checkNull()
            }
        })
    }
}