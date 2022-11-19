package com.tmdhoon.togather.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentPostBinding
import com.tmdhoon.togather.dto.request.data.Tags
import com.tmdhoon.togather.util.printToast
import com.tmdhoon.togather.viewmodel.PostViewModel

class PostFragment : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentPostBinding

    private val postViewModel : PostViewModel by lazy {
        PostViewModel()
    }

    private val tagList : ArrayList<Tags> by lazy {
        ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_post,
            container,
            false,
        )

        initCloseButton()
        initPostButton()
        initTagButton()
        initPostObserve()

        return binding.root
    }

    override fun onCreateDialog(
        savedInstanceState: Bundle?,
    ): Dialog =
        BottomSheetDialog(
            requireContext(),
            theme,
        ).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }


    private fun initPostObserve() {
        postViewModel.postResponse.observe(viewLifecycleOwner){
            when(it.code()){
                201 ->{
                    printToast(
                        context = view?.context,
                        message = "글이 정상적으로 등록되었습니다!",
                        )
                    tagList.clear()
                    dismiss()
                }
                400 -> {
                    printToast(
                        context = view?.context,
                        message = "값이 잘못되었습니다!",
                    )
                }
            }
        }
    }

    private fun initTagButton() {
        binding.btPostTag.setOnClickListener {
            TagFragment().show(
                parentFragmentManager,
                TagFragment().tag,
            )
        }
    }

    private fun initPostButton() {
        binding.btPostPost.setOnClickListener {
            if(
                binding.etPostTitle.text.isNotEmpty()
                && binding.etPostMain.text.isNotEmpty()
            ){
                postViewModel.post(
                    binding.etPostTitle.text.toString(),
                    tagList,
                    binding.etPostMain.text.toString(),
                )
            }
        }
    }

    private fun initCloseButton() {
        binding.imgPostClose.setOnClickListener {
            dismiss()
        }
    }
}