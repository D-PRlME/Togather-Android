package com.tmdhoon.togather.view.fragment

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentHomeBinding
import com.tmdhoon.togather.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    companion object{
        fun newInstance() : SearchFragment{
            return SearchFragment()
        }
    }

    private lateinit var binding : FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        return binding.root
    }
}