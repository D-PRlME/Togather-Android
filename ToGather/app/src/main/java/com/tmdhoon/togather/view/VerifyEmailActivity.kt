package com.tmdhoon.togather.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ActivityVerifyEmailBinding
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.util.IntentUtil
import com.tmdhoon.togather.util.SharedPreferencesUtil
import com.tmdhoon.togather.util.ToastUtil
import com.tmdhoon.togather.viewmodel.LoginViewModel
import com.tmdhoon.togather.viewmodel.RegisterViewModel

class VerifyEmailActivity : AppCompatActivity() {

    private val binding: ActivityVerifyEmailBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_verify_email)
    }

    private val registerViewModel: RegisterViewModel by lazy {
        ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    private val loginViewModel : LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        initNextButton()
        initObserveVerifyCode()
        initObserveRegister()
    }

    private fun initNextButton() {
        val code = binding.etVerifyEmailCode.text
        binding.btVerifyEmailNext.setOnClickListener {
            if (code.isNotBlank()) {
                registerViewModel.verifyCode(
                    SharedPreferencesUtil.get("email", ""),
                    Integer.parseInt(code.toString())
                )
            }
        }
    }

    private fun initObserveVerifyCode() {
        registerViewModel.verifyCodeResponse.observe(this, Observer {
            when (it.code()) {
                204 -> {
                    ToastUtil.print(this, "이메일이 성공적으로 인증되었습니다!")
                    registerViewModel.register(
                        SharedPreferencesUtil.get("email", ""),
                        SharedPreferencesUtil.get("pw", ""),
                        SharedPreferencesUtil.get("name", "")
                    )
                }
                401 -> {
                    ToastUtil.print(this, "인증코드가 잘못되었습니다!")
                }

            }
        })
    }

    private fun initObserveRegister() {
        registerViewModel.registerResponse.observe(this, Observer {
            when(it.code()){
                201 ->{
                    IntentUtil.startIntent(this, SuccessActivity::class.java)
                    finish()
                }
                400 ->{
                    ToastUtil.print(this, "항목을 확인해주세요!")
                }
                401 ->{
                    ToastUtil.print(this, "인증되지 않은 이메일 입니다")
                }
                409 ->{
                    ToastUtil.print(this, "중복된 아이디 입니다")
                }
            }
        })
    }
}