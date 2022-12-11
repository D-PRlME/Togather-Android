package com.tmdhoon.togather.view.fragment

import android.app.Activity
import android.app.Dialog
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentPostBinding
import com.tmdhoon.togather.dto.request.data.Tags
import com.tmdhoon.togather.util.*
import com.tmdhoon.togather.viewmodel.MainViewModel
import com.tmdhoon.togather.viewmodel.PostViewModel
import com.tmdhoon.togather.viewmodel.PostViewModelFactory

class PostFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPostBinding

    private val postViewModelFactory by lazy {
        PostViewModelFactory(requireContext())
    }

    private val postViewModel: PostViewModel by lazy {
        ViewModelProvider(this, postViewModelFactory)[PostViewModel::class.java]
    }

    private val mainViewModel : MainViewModel by lazy {
        MainViewModel()
    }

    var tagList : ArrayList<String> = ArrayList()

    private val pref: SharedPreferences by lazy {
        initPref(
            context = requireContext(),
        )
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
        observePostResponse()
        initailizeEditPost()
        observeEditPostResponse()

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

    override fun onDetach() {
        super.onDetach()
    }

    private fun initailizeEditPost() {
        if (getPref(
                preferences = pref,
                key = "isEdited",
                value = false,
            ) as Boolean
        ) {
            binding.run {
                setCursor(
                    view = etPostTitle,
                    length = etPostTitle.length()
                )

                setCursor(
                    view = etPostContent,
                    length = etPostContent.length()
                )

                etPostTitle.text =
                    Editable.Factory.getInstance().newEditable(
                        getPref(
                            preferences = pref,
                            key = "title",
                            value = "",
                        ).toString()
                    )

                etPostContent.text =
                    Editable.Factory.getInstance().newEditable(
                        getPref(
                            preferences = pref,
                            key = "content",
                            value = "",
                        ).toString()
                    )
                btPostPost.text = getString(
                    R.string.detail_do_edit
                )
            }
        }
    }

    private fun initPostButton() {
        binding.btPostPost.setOnClickListener {
            if(getPref(
                    preferences = pref,
                    key = "isEdited",
                    value = false,
                ) as Boolean){
                if (
                    binding.etPostTitle.text.isNotEmpty()
                    && binding.etPostContent.text.isNotEmpty()
                ) {
                    postViewModel.editPost(
                        title = binding.etPostTitle.text.toString(),
                        content = binding.etPostContent.text.toString(),
                        postId = getPref(
                            preferences = pref,
                            key = "postId",
                            value = 0,
                        ) as Int,
                        tagList = tagList,
                    )
                }
            }else {
                if (
                    binding.etPostTitle.text.isNotEmpty()
                    && binding.etPostContent.text.isNotEmpty()
                ) {
                    postViewModel.post(
                        title = binding.etPostTitle.text.toString(),
                        content = binding.etPostContent.text.toString(),
                    )
                }
            }
        }
    }

    private fun observeEditPostResponse(){
        postViewModel.editResponse.observe(viewLifecycleOwner){
            when(it.code()){
                204->{
                    printToast(
                        context = view?.context,
                        message = "글이 성공적으로 수정되었습니다!",
                    )
                    dismiss()
                }
                400 ->{
                    printToast(
                        context = view?.context,
                        message = "값이 잘못되었습니다!",
                    )
                }
            }
        }
    }

    private fun observePostResponse() {
        postViewModel.postResponse.observe(viewLifecycleOwner) {

            when (it.code()) {
                201 -> {
                    printToast(
                        context = view?.context,
                        message = "글이 정상적으로 등록되었습니다!",
                    )
                    dismiss()
                }
                400 -> {
                    Log.d("TEST", it.errorBody()!!.string())
                    printToast(
                        context = view?.context,
                        message = "값이 잘못되었습니다!",
                    )
                }
            }
        }
    }

    fun setCursor(
        view: EditText,
        length: Int,
    ) {
        view.setSelection(length)
    }

    private fun initTagButton() {
        binding.btPostTag.setOnClickListener {
            createTagFragment(requireContext(), mainViewModel, viewLifecycleOwner, postViewModel)
        }
    }

    private fun initCloseButton() {
        binding.imgPostClose.setOnClickListener {
            dismiss()
        }
    }
}