package com.tmdhoon.togather.view.fragment

import android.app.Dialog
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentDetailBinding
import com.tmdhoon.togather.dto.response.data.Tags
import com.tmdhoon.togather.remote.MainTagListAdapter
import com.tmdhoon.togather.util.*
import com.tmdhoon.togather.view.ChatActivity
import com.tmdhoon.togather.viewmodel.DetailViewModel


class DetailFragment : BottomSheetDialogFragment() {

    private val detailViewModel: DetailViewModel by lazy {
        DetailViewModel()
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

    private var postId: Int = 0

    private var likeCount : Int = 0

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(
            requireContext(),
            theme,
        ).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail,
            container,
            false,
        )
        binding.lifecycleOwner = this
        binding.detailFragment = this
        binding.detailViewModel = detailViewModel
        observeDetailResponse()
        observeLikeOnResponse()
        observeLikeOffResponse()
        observeDeletePostResponse()
        getPostDetail()
        refresh()
        initContactButton()

        return binding.root
    }

    private fun refresh() {
        if (getPref(
                preferences = pref,
                key = "isEdited",
                value = false,
            ) as Boolean
        ) {
            getPostDetail()
            putPref(
                editor = pref.edit(),
                key = "isEdited",
                value = false
            )
        }
    }

    private fun getPostDetail() {
        detailViewModel.getPosts(
            getPref(
                preferences = pref,
                key = "postId",
                value = 0,
            ) as Int
        )
    }

    fun editPost() {
        putPref(
            editor = pref.edit(),
            key = "isEdited",
            value = true,
        )
        PostFragment().show(
            parentFragmentManager,
            PostFragment().tag,
        )
        putPref(
            editor = pref.edit(),
            key = "title",
            value = binding.tvDetailTitle.text,
        )
        putPref(
            editor = pref.edit(),
            key = "content",
            value = binding.tvDetailContent.text,
        )
    }

    fun like() {
        if (getPref(
                preferences = pref,
                key = "like$postId",
                value = false,
            ) as Boolean
        ) {
           binding.btDetailLike.text = likeCount.toString()
            setLikeOff()
            detailViewModel.unLike(postId)
        } else {
            binding.btDetailLike.text = (likeCount+1).toString()
            setLikeOn()
            detailViewModel.like(postId)
        }
    }

    private fun observeLikeOnResponse() {
        detailViewModel.likeOnResponse.observe(viewLifecycleOwner) {
            when (it.code()) {
                204 -> {
                    setLikeOn()
                    putPref(
                        editor = pref.edit(),
                        key = "like$postId",
                        value = true,
                    )
                }
            }
        }
    }

    private fun observeLikeOffResponse() {
        detailViewModel.likeOffResponse.observe(viewLifecycleOwner) {
            when (it.code()) {
                204 -> {
                    setLikeOff()
                    putPref(
                        editor = pref.edit(),
                        key = "like$postId",
                        value = false,
                    )
                }
            }
        }
    }

    private fun setLikeOn() {
        binding.imgDetailLike.setImageDrawable(
            getDrawable(
                requireContext(),
                R.drawable.ic_detail_like_on,
            ),
        )
    }

    private fun setLikeOff() {
        binding.imgDetailLike.setImageDrawable(
            getDrawable(
                requireContext(),
                R.drawable.ic_detail_like_off,
            ),
        )
    }

    fun deletePost() {
        detailViewModel.deletePost(postId)
    }

    private fun observeDeletePostResponse() {
        detailViewModel.deleteResponse.observe(viewLifecycleOwner) {
            when (it.code()) {
                204 -> {
                    printToast(
                        context = requireContext(),
                        message = "성공적으로 삭제되었습니다!"
                    )
                    dismiss()
                }
            }
        }
    }

    private fun observeDetailResponse() {
        detailViewModel.detailResponse.observe(viewLifecycleOwner) {
            when (it.code()) {
                200 -> {
                    postId = getPref(
                        preferences = pref,
                        key = "postId",
                        value = 0,
                    ) as Int
                    tagList.clear()
                    binding.rvDetailTag.run {
                        adapter = MainTagListAdapter(it.body()!!.tags)
                        layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false,
                        )
                    }
                    if (it.body()!!.is_liked) {
                        setLikeOn()
                        putPref(
                            editor = pref.edit(),
                            key = "like$postId",
                            value = true,
                        )
                        likeCount = (it.body()!!.like_count - 1).toInt()

                    } else {
                        setLikeOff()
                        putPref(
                            editor = pref.edit(),
                            key = "like$postId",
                            value = false,
                        )
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

    private fun initContactButton(){
        binding.btDetailContact.setOnClickListener {
            startIntent(
                context = requireContext(),
                to = ChatActivity::class.java,
            )
        }
    }
}