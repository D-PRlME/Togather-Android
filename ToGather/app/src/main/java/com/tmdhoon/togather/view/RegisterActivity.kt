package com.tmdhoon.togather.view

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ActivityRegisterBinding
import com.tmdhoon.togather.util.*
import com.tmdhoon.togather.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private val binding: ActivityRegisterBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_register)
    }

    private val registerViewModel: RegisterViewModel by lazy {
        ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    private val pref: SharedPreferences by lazy {
        initPref(this, MODE_PRIVATE)
    }

    private val editor: SharedPreferences.Editor by lazy {
        pref.edit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        initRegisterButton()
        initObserveRegister()
        initObserveDuplicate()
        initObserveChangeEmailEdittext()
        initObserveChangePwEdittext()
        initObserveChangeNameEdittext()
    }

    private fun checkNull(){
        val email = binding.etRegisterEmail.text
        val pw = binding.etRegisterPw.text
        val name = binding.etRegisterName.text
        if(email.isNotEmpty() && pw.isNotEmpty() && name.isNotEmpty()){
            binding.btRegisterNext.setBackgroundResource(R.drawable.button_yellow)
            binding.btRegisterNext.setTextColor(Color.BLACK)
        }else{
            binding.btRegisterNext.setBackgroundResource(R.drawable.button_white)
            binding.btRegisterNext.setTextColor(R.color.all_background_focusOut)
        }
    }

    private fun initObserveChangeEmailEdittext() {
        binding.etRegisterEmail.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = binding.etRegisterEmail.text
                val domain = email.split("@")
                if(domain.size>=2){
                    if(!domain[1].equals("dsm.hs.kr")){
                        binding.etRegisterEmail.setBackgroundResource(R.drawable.edittext_error)
                    }else{
                        binding.etRegisterEmail.setBackgroundResource(R.drawable.edittext_courner)
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {
                checkNull()
            }
        })
    }

    private fun initObserveChangePwEdittext(){
        binding.etRegisterPw.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                checkNull()
            }

        })
    }

    private fun initObserveChangeNameEdittext() {
        binding.etRegisterName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                checkNull()
            }
        })
    }

    private fun initRegisterButton() {
        binding.btRegisterNext.setOnClickListener {
            val email = binding.etRegisterEmail.text.toString()
            val pw = binding.etRegisterPw.text.toString()
            val name = binding.etRegisterName.text.toString()
            if (email != "" && pw != "" && name != "") {
                if (getPref(pref, "VerifyEmail", false) as Boolean) {
                    registerViewModel.register(email, pw, name)
                    initPreferences(email, pw, name)
                    putPref(editor, "VerifyEmail", false)
                } else {
                    registerViewModel.duplicate(email)
                    initPreferences(email, pw, name)
                }
            }
        }
    }

    private fun initObserveDuplicate() {
        registerViewModel.duplicateResponse.observe(this, Observer {
            when (it.code()) {
                204 -> {
                    ToastUtil.print(this, "인증 메일이 전송되었습니다!")
                    registerViewModel.verifyEmail(
                        getPref(pref, "email", "").toString()
                    )
                    IntentUtil.startIntent(this, VerifyEmailActivity::class.java)
                    finish()
                }
                400 -> ToastUtil.print(this, "이메일 형식이 잘못되었습니다!")
                409 -> ToastUtil.print(this, "중복된 이메일 입니다")
            }
        })
    }

    private fun initPreferences(email: String, pw: String, name: String) {
        putPref(editor, "email", email)
        putPref(editor, "pw", pw)
        putPref(editor, "name", name)
    }

    private fun initObserveRegister() {
        registerViewModel.registerResponse.observe(this, Observer {
            when (it.code()) {
                201 -> {
                    IntentUtil.startIntent(this, SuccessActivity::class.java)
                    ACCESS_TOKEN = it.body()!!.access_token
                    finish()
                }
                400 -> ToastUtil.print(this, "항목을 확인해주세요!")
                401 -> ToastUtil.print(this, "인증되지 않은 이메일 입니다")
                409 -> ToastUtil.print(this, "중복된 아이디 입니다")
            }
        })
    }
}