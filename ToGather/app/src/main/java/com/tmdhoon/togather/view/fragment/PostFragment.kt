package com.tmdhoon.togather.view.fragment

import android.app.Dialog
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentPostBinding
import com.tmdhoon.togather.dto.request.data.Tags
import com.tmdhoon.togather.util.getPref
import com.tmdhoon.togather.util.initPref
import com.tmdhoon.togather.util.printToast
import com.tmdhoon.togather.viewmodel.PostViewModel

class PostFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPostBinding

    private val postViewModel: PostViewModel by lazy {
        PostViewModel()
    }

    private val tagList: ArrayList<Tags> by lazy {
        ArrayList()
    }

    private val pref: SharedPreferences by lazy {
        initPref(
            context = requireContext(),
            mode = MODE_PRIVATE,
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

        Log.d("TEST", getPref(pref, "isEdited", false).toString())

        initCloseButton()
        initPostButton()
        initTagButton()
        initPostObserve()
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

    private fun observeEditPostResponse(){
        postViewModel.editResponse.observe(viewLifecycleOwner){
            when(it.code()){
                204->{
                    printToast(
                        context = view?.context,
                        message = "글이 성공적으로 수정되었습니다!",
                    )
                    tagList.clear()
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

    private fun initPostObserve() {
        postViewModel.postResponse.observe(viewLifecycleOwner) {
            when (it.code()) {
                201 -> {
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

    private fun initailizeEditPost() {
        if (getPref(
                preferences = pref,
                key = "isEdited",
                value = false,
        ) as Boolean) {
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

    fun setCursor(
        view: EditText,
        length: Int,
    ) {
        view.setSelection(length)
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
                        tags = tagList,
                        content = binding.etPostContent.text.toString(),
                        postId = getPref(
                            preferences = pref,
                            key = "postId",
                            value = 0,
                        ) as Int
                    )
                }
            }else {
                if (
                    binding.etPostTitle.text.isNotEmpty()
                    && binding.etPostContent.text.isNotEmpty()
                ) {
                    postViewModel.post(
                        title = binding.etPostTitle.text.toString(),
                        tags = tagList,
                        content = binding.etPostContent.text.toString(),
                    )
                }
            }
        }
    }

    private fun initCloseButton() {
        binding.imgPostClose.setOnClickListener {
            dismiss()
        }
    }
}