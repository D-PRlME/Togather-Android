package com.tmdhoon.togather.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ActivityChangePwBinding
import com.tmdhoon.togather.util.ToastUtil
import com.tmdhoon.togather.viewmodel.ChangePwViewModel
import com.tmdhoon.togather.viewmodel.RegisterViewModel

class ChangePwActivity : AppCompatActivity() {

    private val binding : ActivityChangePwBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_change_pw)
    }

    private val changePwViewModel : ChangePwViewModel by lazy {
        ViewModelProvider(this).get(ChangePwViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserveEmailEdittext()
        initNextButton()
        initObserveVerifyEmailResponse()
        binding.lifecycleOwner = this
    }

    private fun initNextButton() {
        binding.btChangePwNext.setOnClickListener {
            val email = binding.etChangePwEmail.text.toString()
            if(email.isNotEmpty()){
                changePwViewModel.changePwVerifyEmail(email)
                Log.d("TEST", email)
            }
        }
    }

    private fun initObserveVerifyEmailResponse(){
        changePwViewModel.changePwVerifyEmailResponse.observe(this, Observer {
            when(it.code()){
                204 -> ToastUtil.print(this, "인증 메일이 전송되었습니다!")
            }
        })
    }

    private fun checkNull(){
        val email = binding.etChangePwEmail.text
        if(email.isNotEmpty()){
            binding.btChangePwNext.setBackgroundResource(R.drawable.button_yellow)
            binding.btChangePwNext.setTextColor(Color.BLACK)
        }else{
            binding.btChangePwNext.setBackgroundResource(R.drawable.button_white)
            binding.btChangePwNext.setTextColor(getColor(R.color.all_background_focusOut))
        }
    }

    private fun initObserveEmailEdittext() {
        binding.etChangePwEmail.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                checkNull()
            }

        })
    }
}