package com.tmdhoon.togather.view.fragment

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentMyinfoBinding
import com.tmdhoon.togather.remote.PositionAdapter
import com.tmdhoon.togather.util.*
import com.tmdhoon.togather.view.AuthChangePwActivity
import com.tmdhoon.togather.view.LoginActivity
import com.tmdhoon.togather.viewmodel.ChangePwViewModel
import com.tmdhoon.togather.viewmodel.LogoutViewModel
import com.tmdhoon.togather.viewmodel.MyInfoViewModel

class MyInfoFragment : Fragment() {

    private lateinit var binding : FragmentMyinfoBinding

    private val myInfoViewModel : MyInfoViewModel by lazy {
        ViewModelProvider(this).get(MyInfoViewModel::class.java)
    }

    private val changePwViewModel : ChangePwViewModel by lazy {
        ViewModelProvider(this).get(ChangePwViewModel::class.java)
    }

    private val logoutViewModel : LogoutViewModel by lazy {
        ViewModelProvider(this).get(LogoutViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initDataBinding(inflater, container)
        initRequest()
        initEditInfoButton()
        observeMyInfo()
        observeLogout()

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
        myInfoViewModel.myInfoResponse.observe(viewLifecycleOwner) {
            putPref(initPref(this.requireContext(), MODE_PRIVATE).edit(), "email", it.body()!!.email)
            binding.rvMyInfoPosition.run {
                adapter = PositionAdapter(it.body()!!.positions)
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    private fun initEditInfoButton(){
        binding.btMypageEditInfo.setOnClickListener {
            val bottomSheetFragment = AccountEditFragment()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }
    }

    fun changePw(){
        startIntent(this.requireContext(), AuthChangePwActivity::class.java)
        changePwViewModel.changePwVerifyEmail(getPref(initPref(this.requireContext(), MODE_PRIVATE), "email", "").toString())
    }

    fun showMyPost(){
        val myPostFragment = MyPostFragment()
        myPostFragment.show(parentFragmentManager, myPostFragment.tag)
    }

    fun logout(){
        logoutViewModel.logout()
    }

    private fun observeLogout(){
        logoutViewModel.logoutResponse.observe(viewLifecycleOwner){
            when(it.code()){
                204 -> {
                    printToast(this.requireContext(), "로그아웃 되었습니다")
                    startIntent(this.requireContext(), LoginActivity::class.java)
                    requireActivity().finish()
                }
            }
        }
    }
}