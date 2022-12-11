package com.tmdhoon.togather.view

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tmdhoon.togather.R
import com.tmdhoon.togather.base.BaseActivity
import com.tmdhoon.togather.databinding.ActivityAuthChangePwBinding
import com.tmdhoon.togather.util.*
import com.tmdhoon.togather.viewmodel.MyInfoViewModel
import com.tmdhoon.togather.viewmodel.RegisterViewModel

class AuthChangePwActivity : BaseActivity<ActivityAuthChangePwBinding>(R.layout.activity_auth_change_pw) {

    private val registerViewModel : RegisterViewModel by lazy {
        ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    private val myInfoViewModel : MyInfoViewModel by lazy {
        ViewModelProvider(this).get(MyInfoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeEmail()
        initEditTextChangedListener()
        observeCodeResponse()
        binding.authChangePwActivity = this
    }

    private fun checkNull(){
        val code = binding.etAuthChangePwCode.text
        if(code.length == 6){
            binding.btAuthChangePwNext.setBackgroundResource(R.drawable.button_yellow)
            binding.btAuthChangePwNext.setTextColor(Color.BLACK)
        }else{
            binding.btAuthChangePwNext.setBackgroundResource(R.drawable.button_white)
            binding.btAuthChangePwNext.setTextColor(R.color.all_background_focusOut)
        }
    }

    private fun initEditTextChangedListener(){
        binding.etAuthChangePwCode.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                checkNull()
            }
        })
    }

    private fun observeEmail(){
        myInfoViewModel.myInfoResponse.observe(this, Observer {

        })
    }

    fun initNextButton(){
        val email = getPref(initPref(this), "email", "")
        binding.btAuthChangePwNext.setOnClickListener {
            val code = binding.etAuthChangePwCode.text
            if(code.isNotEmpty()){
                registerViewModel.verifyCode(email.toString(), code.toString())
            }
        }
    }

    private fun observeCodeResponse(){
        registerViewModel.verifyCodeResponse.observe(this, Observer {
            when(it.code()){
                204 -> {
                    printToast(this, "인증되었습니다!")
                    startIntent(this, NewPwActivity::class.java)
                    finish()
                }
                400 ->{
                    printToast(this, "인증번호가 다릅니다")
                }
            }
        })
    }

}