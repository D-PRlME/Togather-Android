package com.tmdhoon.togather.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
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

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        return dialog
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
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_tag, container, false)
    }

}