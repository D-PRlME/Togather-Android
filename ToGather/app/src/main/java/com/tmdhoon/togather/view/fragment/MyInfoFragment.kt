package com.tmdhoon.togather.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentMyinfoBinding
import com.tmdhoon.togather.viewmodel.MyInfoViewModel

class MyInfoFragment : Fragment() {

    companion object{
        fun newInstance() : MyInfoFragment{
            return MyInfoFragment()
        }
    }

    private lateinit var binding : FragmentMyinfoBinding

    private val myInfoViewModel : MyInfoViewModel by lazy {
        MyInfoViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initDataBinding(inflater, container)
        initRequest()

        return binding.root
    }

    private fun initRequest() {
        myInfoViewModel.myInfo()
    }

    private fun initDataBinding(inflater:LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_myinfo, container, false)
        binding.user = myInfoViewModel
        binding.lifecycleOwner = this
    }
}