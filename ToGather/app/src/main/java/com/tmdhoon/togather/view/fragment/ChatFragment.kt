package com.tmdhoon.togather.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }
}