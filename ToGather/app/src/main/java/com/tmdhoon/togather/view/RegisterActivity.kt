package com.tmdhoon.togather.view

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ActivityRegisterBinding
import com.tmdhoon.togather.util.IntentUtil
import com.tmdhoon.togather.util.SharedPreferencesUtil
import com.tmdhoon.togather.util.ToastUtil
import com.tmdhoon.togather.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private val binding : ActivityRegisterBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_register)
    }

    private val registerViewModel : RegisterViewModel by lazy {
        ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        initRegisterButton()
    }

    private fun initRegisterButton() {
        binding.btRegisterNext.setOnClickListener {
            val email = binding.etRegisterEmail.text.toString()
            val pw = binding.etRegisterPw.text.toString()
            val name = binding.etRegisterName.text.toString()
            if(email != "" && pw != "" && name != "") registerViewModel.duplicate(email)
            initObserveDuplicate(email, pw, name)

        }
    }

    private fun initObserveDuplicate(email : String, pw : String, name : String) {
        registerViewModel.duplicateResponse.observe(this, Observer {
            when(it.code()){
                204 -> {
                    ToastUtil.print(this, "인증 메일이 전송되었습니다!")
                    initPreferences(email, pw, name)
                    registerViewModel.verifyEmail(email)
                    IntentUtil.startIntent(this, VerifyEmailActivity::class.java)
                    finish()

                }
                400 -> ToastUtil.print(this, "이메일 형식이 잘못되었습니다!")
                409 -> ToastUtil.print(this, "중복된 이메일 입니다")
            }
        })
    }

    private fun initPreferences(email : String, pw : String, name : String){
        SharedPreferencesUtil.put("email", email)
        SharedPreferencesUtil.put("pw", pw)
        SharedPreferencesUtil.put("name", name)
    }
}