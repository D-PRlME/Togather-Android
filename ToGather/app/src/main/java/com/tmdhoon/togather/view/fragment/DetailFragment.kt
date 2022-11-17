package com.tmdhoon.togather.view.fragment

import android.app.Dialog
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.print.PrintAttributes.Margins
import android.util.Log
import android.view.Display

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentDetailBinding
import com.tmdhoon.togather.dto.response.data.Tags
import com.tmdhoon.togather.remote.MainTagAdapter
import com.tmdhoon.togather.remote.MainTagListAdapter
import com.tmdhoon.togather.util.getPref
import com.tmdhoon.togather.util.initPref
import com.tmdhoon.togather.viewmodel.DetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailFragment : BottomSheetDialogFragment() {

    private val detailViewModel : DetailViewModel by lazy {
        DetailViewModel()
    }

    private val tagList : ArrayList<Tags> by lazy {
        ArrayList()
    }

    private lateinit var binding : FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.lifecycleOwner = this
        binding.detailViewModel = detailViewModel
        observeDetailResponse()

        detailViewModel.getPosts(
            getPref(
                initPref(requireContext(), MODE_PRIVATE),
                "postId",
                0
            ) as Int
        )

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        return dialog
    }

    private fun observeDetailResponse(){
        detailViewModel.detailResponse.observe(viewLifecycleOwner) {

            when(it.code()){
                200->{
                    tagList.addAll(it.body()!!.tags)
                    binding.rvDetailTag.run {
                        adapter = MainTagListAdapter(tagList)
                        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    }
                }
            }

            if (!it.body()!!.is_mine) {
                binding.run {
                    btDetailDelete.visibility = View.INVISIBLE
                    btDetailEdit.visibility = View.INVISIBLE
                }
            }
        }
    }
}