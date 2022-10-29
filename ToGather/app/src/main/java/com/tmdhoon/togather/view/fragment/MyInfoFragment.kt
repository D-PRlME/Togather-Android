package com.tmdhoon.togather.view.fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentMyinfoBinding
import com.tmdhoon.togather.util.initPref
import com.tmdhoon.togather.util.putPref
import com.tmdhoon.togather.util.startIntent
import com.tmdhoon.togather.view.AuthChangePwActivity
import com.tmdhoon.togather.viewmodel.ChangePwViewModel
import com.tmdhoon.togather.viewmodel.MyInfoViewModel

class MyInfoFragment : Fragment() {

    private lateinit var binding : FragmentMyinfoBinding

    private val myInfoViewModel : MyInfoViewModel by lazy {
        ViewModelProvider(this).get(MyInfoViewModel::class.java)
    }

    private val changePwViewModel : ChangePwViewModel by lazy {
        ViewModelProvider(this).get(ChangePwViewModel::class.java)
    }

    private lateinit var email : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initDataBinding(inflater, container)
        initRequest()
        initEditInfoButton()
        observeMyInfo()

        return binding.root
    }

    private fun initRequest() {
        myInfoViewModel.myInfo()
    }

    private fun initDataBinding(inflater:LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_myinfo, container, false)
        binding.myInfoViewModel = myInfoViewModel
        binding.myInfoFragment = this
        binding.lifecycleOwner = this
    }

    private fun observeMyInfo(){
        myInfoViewModel.myInfoResponse.observe(this, Observer {
            email = it.body()!!.email
        })
    }

    private fun initEditInfoButton(){
        binding.btMypageEditInfo.setOnClickListener {
            val bottomSheetFragment = AccountEditFragment()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }
    }

    fun changePw(){
        startIntent(this.requireContext(), AuthChangePwActivity::class.java)
        changePwViewModel.changePwVerifyEmail(email)
    }
}