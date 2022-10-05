package com.tmdhoon.togather.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentTagBinding
import com.tmdhoon.togather.model.response.data.Tags
import com.tmdhoon.togather.remote.TagAdapter
import com.tmdhoon.togather.viewmodel.MainViewModel

class TagFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentTagBinding

    private val tagList: ArrayList<Tags> by lazy {
        ArrayList()
    }

    private val mainViewModel: MainViewModel by lazy {
        MainViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        initDataBinding(inflater, container)
        initRequest()
        initObserve()

        return binding.root
    }

    private fun initObserve() {
        mainViewModel.tagResponse.observe(this, Observer {
            when (it.code()) {
                200 -> {
                    initRecyclerView()
                    tagList.addAll(it.body()!!.tags)
                }
            }
        })
    }

    private fun initRequest() {
        mainViewModel.tag()
    }

    private fun initRecyclerView() {
        binding.rvTagRecyclerView.adapter = TagAdapter(tagList)
        binding.rvTagRecyclerView.layoutManager = LinearLayoutManager(view?.context)

    }

    private fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tag, container, false)
    }

}