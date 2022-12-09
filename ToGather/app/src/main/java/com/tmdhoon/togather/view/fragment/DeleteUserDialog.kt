package com.tmdhoon.togather.view.fragment

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.DialogDeleteUserBinding
import com.tmdhoon.togather.util.*
import com.tmdhoon.togather.view.LoginActivity
import com.tmdhoon.togather.viewmodel.MyInfoViewModel

class DeleteUserDialog : DialogFragment() {

    private val pref : SharedPreferences by lazy {
        initPref(this.requireContext(), MODE_PRIVATE)
    }

    private val myInfoViewModel : MyInfoViewModel by lazy {
        MyInfoViewModel()
    }

    private lateinit var binding : DialogDeleteUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)

        observeDeleteUser()

        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_delete_user, container, false)
        binding.deleteUserDialog = this
        return binding.root
    }

    fun confirmButton(){
        myInfoViewModel.deleteUser(getPref(pref, "password", "").toString())
    }

    fun cancelButton(){
        dismiss()
    }

    private fun observeDeleteUser(){
        myInfoViewModel.deleteUserResponse.observe(this, Observer {
            when(it.code()){
                204->{
                    printToast(this.requireContext(), "성공적으로 처리되었습니다")
                    dismiss()
                    startIntent(this.requireContext(), LoginActivity::class.java)
                    ACCESS_TOKEN = ""
                }
                403 ->{
                    printToast(this.requireContext(), "비밀번호가 다릅니다")
                    dismiss()
                }
            }
        })
    }



}