package com.tmdhoon.togather.view

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ActivityLoginBinding
import com.tmdhoon.togather.util.*
import com.tmdhoon.togather.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    private val loginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        initSplashScreen()
        initLoginObserve()
        initLoginButton()
        initRegisterText()
        initChangePwText()
    }

    private fun initSplashScreen() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                ObjectAnimator.ofFloat(splashScreenView, View.ALPHA, 1f, 0f).run {
                    interpolator = AnticipateInterpolator()
                    duration = 1500L
                    doOnEnd { splashScreenView.remove() }
                    start()
                }
            }
        }
    }

    private fun initChangePwText() {
        binding.tvLoginChangePw.setOnClickListener{
            IntentUtil.startIntent(this, ChangePwActivity::class.java)
        }
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
                    ACCESS_TOKEN = it.body()!!.access_token
                    ToastUtil.print(this, "로그인에 성공하였습니다!")
                    IntentUtil.startIntent(this, MainActivity::class.java)
                    finish()
                    putPref(initPref(this, MODE_PRIVATE).edit(), "code", 200)
                    Log.d("TEST", getPref(initPref(this, MODE_PRIVATE), "code", 0).toString())
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

            if (email.isNotEmpty() && pw.isNotEmpty()) {
                loginViewModel.login(email, pw)
            }
        }
    }

}