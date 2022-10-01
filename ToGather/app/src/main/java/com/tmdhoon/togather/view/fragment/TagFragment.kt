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

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tag, container, false)

        binding.rvTagRecyclerView.adapter = TagAdapter(tagList)

        mainViewModel.tag()

        mainViewModel.tagResponse.observe(this, Observer {
            when (it.code()) {
                200 -> {
                    tagList.addAll(it.body()!!.tags)
                    binding.rvTagRecyclerView.layoutManager = LinearLayoutManager(view?.context)
                }
            }
        })


        return binding.root
    }

}