package com.tmdhoon.togather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ActivityLoginBinding
import com.tmdhoon.togather.repository.Repository
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.util.IntentUtil
import com.tmdhoon.togather.util.ToastUtil
import com.tmdhoon.togather.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private val binding : ActivityLoginBinding by lazy{
        DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    private val loginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel.loginResponse.observe(this, Observer {
            if (it.isSuccessful) {
                ToastUtil.print(applicationContext, "로그인에 성공하였습니다!")
                ACCESS_TOKEN = it.body()!!.access_token
                IntentUtil.startIntent(this, MainActivity::class.java)
                finish()
            }
        })


        binding.btLoginLogin.setOnClickListener {
            val email = binding.etLoginEmail.text.toString()
            val pw = binding.etLoginPw.text.toString()

            if (email != "" && pw != "") {
                loginViewModel.login(email, pw)
            }
        }
    }
}