package com.tmdhoon.togather.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.addTextChangedListener
import com.tmdhoon.togather.R
import com.tmdhoon.togather.base.BaseActivity
import com.tmdhoon.togather.databinding.ActivityAuthChangePwBinding

class AuthChangePwActivity : BaseActivity<ActivityAuthChangePwBinding>(R.layout.activity_auth_change_pw) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initEditTextChangedListener()
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
}