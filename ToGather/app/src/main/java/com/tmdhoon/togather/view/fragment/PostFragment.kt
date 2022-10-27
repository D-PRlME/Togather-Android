package com.tmdhoon.togather.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.BottomSheetPostBinding
import com.tmdhoon.togather.dto.request.data.Tags
import com.tmdhoon.togather.util.printToast
import com.tmdhoon.togather.viewmodel.PostViewModel

class PostFragment : BottomSheetDialogFragment() {

    private lateinit var binding : BottomSheetPostBinding

    private val postViewModel : PostViewModel by lazy {
        PostViewModel()
    }

    private val tagList : ArrayList<Tags> by lazy {
        ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initDataBinding(inflater, container)
        initCloseButton()
        initPostButton()
        initTagButton()
        initPostObserve()

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        return dialog
    }

    private fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_post, container, false)
    }

    private fun initPostObserve() {
        postViewModel.postResponse.observe(this, Observer {
            when(it.code()){
                201 ->{
                    printToast(view?.context, "글이 정상적으로 등록되었습니다!")
                    binding.etPostMain.text = null
                    binding.etPostTitle.text = null
                    binding.etPostLink.text = null
                    tagList.clear()
                }
                400 -> {
                    printToast(view?.context, "값이 잘못되었습니다!")
                }
            }
        })
    }

    private fun initTagButton() {
        binding.btPostTag.setOnClickListener {
            val bottomSheetFragment = TagFragment()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }
    }

    private fun initPostButton() {
        binding.btPostPost.setOnClickListener {
            val title = binding.etPostTitle.text.toString()
            val link = binding.etPostLink.text.toString()
            val main = binding.etPostMain.text.toString()
            if(title != "" && link != "" && main != ""){
                postViewModel.post(title, tagList, link, main)
            }
        }
    }

    private fun initCloseButton() {
        binding.imgPostClose.setOnClickListener {
            dismiss()
        }
    }
}