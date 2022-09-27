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
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.viewmodel.MainViewModel

class HomeFragment : Fragment() {

    companion object{
        fun newInstance() : HomeFragment{
            return HomeFragment()
        }
    }

    private lateinit var binding : FragmentHomeBinding

    private val mainViewModel : MainViewModel by lazy {
        MainViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        mainViewModel.tag(ACCESS_TOKEN)

        binding.tagInfo = mainViewModel

        binding.lifecycleOwner = this

        return binding.root
    }
}