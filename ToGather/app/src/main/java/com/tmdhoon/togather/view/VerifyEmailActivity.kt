package com.tmdhoon.togather.view

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ActivityVerifyEmailBinding
import com.tmdhoon.togather.util.*
import com.tmdhoon.togather.viewmodel.RegisterViewModel

class VerifyEmailActivity : AppCompatActivity() {

    private val binding: ActivityVerifyEmailBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_verify_email)
    }

    private val registerViewModel: RegisterViewModel by lazy {
        ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    private val pref : SharedPreferences by lazy {
        initPref(this)
    }

    private val editor : SharedPreferences.Editor by lazy {
        pref.edit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        initNextButton()
        initObserveVerifyCode()
        initObserveRegister()
        initVerifyCodeEdittext()
    }

    private fun initVerifyCodeEdittext() {
        binding.etVerifyEmailCode.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val code = binding.etVerifyEmailCode.text
                if(code.isNotBlank()){
                    binding.btVerifyEmailNext.setBackgroundResource(R.drawable.button_yellow)
                }else{
                    binding.btVerifyEmailNext.setBackgroundResource(R.drawable.button_white)
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun initNextButton() {
        val code = binding.etVerifyEmailCode.text
        binding.btVerifyEmailNext.setOnClickListener {
            if (code.isNotBlank()) {
                registerViewModel.verifyCode(
                    getPref(pref, "email", "").toString(),
                    code.toString()
                )
                Log.d("TEST", getPref(pref, "email", "").toString())
            }
        }
    }

    private fun initObserveVerifyCode() {
        registerViewModel.verifyCodeResponse.observe(this, Observer {
            when (it.code()) {
                204 -> {
                    printToast(this, "???????????? ??????????????? ?????????????????????!")
                    registerViewModel.register(
                        getPref(pref, "email", "").toString(),
                        getPref(pref, "pw", "").toString(),
                        getPref(pref, "name", "").toString(),
                    )
                    putPref(editor, getPref(pref, "email", "").toString(), true)
                }
                401 -> printToast(this, "??????????????? ?????????????????????!")

            }
        })
    }

    private fun initObserveRegister() {
        registerViewModel.registerResponse.observe(this, Observer {
            when (it.code()) {
                201 -> {
                    startIntent(this, SuccessActivity::class.java)
                    ACCESS_TOKEN = it.body()!!.access_token
                    finish()
                }
                400 -> {
                    printToast(this, "????????? ??????????????????!")
                    startIntent(this, RegisterActivity::class.java)
                    finish()
                }
                401 -> printToast(this, "???????????? ?????? ????????? ?????????")
                409 -> printToast(this, "????????? ????????? ?????????")
            }
        })
    }
}