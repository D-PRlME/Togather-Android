package com.tmdhoon.togather.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ActivityLoginBinding
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.util.IntentUtil
import com.tmdhoon.togather.util.ToastUtil
import com.tmdhoon.togather.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    private val loginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLoginObserve()
        initLoginButton()
        initRegisterText()
    }

    private fun initRegisterText() {
        binding.tvLoginRegister.setOnClickListener {
            IntentUtil.startIntent(this, RegisterActivity::class.java)
        }
    }

    private fun initLoginObserve() {
        loginViewModel.loginResponse.observe(this, Observer {
            when (it.code()) {
                200 -> {
                    ToastUtil.print(this, "로그인에 성공하였습니다!")
                    IntentUtil.startIntent(this, MainActivity::class.java)
                    finish()
                }
                400 -> ToastUtil.print(this, "아이디, 비밀번호를 확인해주세요!")
                403 -> ToastUtil.print(this, "비밀번호가 다릅니다!")
                404 -> ToastUtil.print(this, "존재하지 않는 회원입니다!")
            }
        })
    }

    private fun initLoginButton() {
        binding.btLoginLogin.setOnClickListener {
            val email = binding.etLoginEmail.text.toString()
            val pw = binding.etLoginPw.text.toString()

            if (email != "" && pw != "") {
                loginViewModel.login(email, pw)
            }
        }
    }
}