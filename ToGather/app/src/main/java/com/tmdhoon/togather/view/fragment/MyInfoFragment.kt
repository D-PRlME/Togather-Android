package com.tmdhoon.togather.view.fragment

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentHomeBinding
import com.tmdhoon.togather.databinding.FragmentMypageBinding
import com.tmdhoon.togather.viewmodel.MyInfoViewModel

class MyPageFragment : Fragment() {

    companion object{
        fun newInstance() : MyPageFragment{
            return MyPageFragment()
        }
    }

    private lateinit var binding : FragmentMypageBinding

    private val myInfoViewModel : MyInfoViewModel by lazy {
        MyInfoViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage, container, false)

        binding.lifecycleOwner = this

        myInfoViewModel.myInfo()

        return binding.root
    }
}