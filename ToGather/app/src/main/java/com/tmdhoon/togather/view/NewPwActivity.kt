package com.tmdhoon.togather.view

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tmdhoon.togather.R
import com.tmdhoon.togather.base.BaseActivity
import com.tmdhoon.togather.databinding.ActivityNewPwBinding
import com.tmdhoon.togather.util.printToast
import com.tmdhoon.togather.util.startIntent
import com.tmdhoon.togather.viewmodel.ChangePwViewModel

class NewPwActivity : BaseActivity<ActivityNewPwBinding>(R.layout.activity_new_pw) {

    private val changePwViewModel : ChangePwViewModel by lazy {
        ViewModelProvider(this).get(ChangePwViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeNewPwResponse()
        observePwEditText()
        binding.newPwActivity = this
    }

    fun initComplementButton(){
        val newPw = binding.etNewPwPw.text.toString()
        if(newPw.isNotEmpty()){
            changePwViewModel.newPw(newPw)
        }else{
            printToast(this, "비밀번호를 입력해주세요")
        }
    }

    fun observeNewPwResponse(){
        changePwViewModel.newPwResponse.observe(this, Observer {
            when(it.code()){
                204 ->{
                    printToast(this, "비밀번호가 변경되었습니다")
                    startIntent(this, MainActivity::class.java)
                    finish()
                }
            }
        })
    }

    fun observePwEditText(){
        binding.etNewPwPw.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                checkNull()
            }
        })
    }

    private fun checkNull(){
        val pw = binding.etNewPwPw.text
        if(pw.isNotEmpty()){
            binding.btNewPwNext.setBackgroundResource(R.drawable.button_yellow)
            binding.btNewPwNext.setTextColor(Color.BLACK)
        }else{
            binding.btNewPwNext.setBackgroundResource(R.drawable.button_white)
            binding.btNewPwNext.setTextColor(R.color.all_background_focusOut)
        }
    }

}