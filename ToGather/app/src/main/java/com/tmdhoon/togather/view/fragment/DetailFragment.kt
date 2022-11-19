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
import com.tmdhoon.togather.util.getPref
import com.tmdhoon.togather.util.initPref
import com.tmdhoon.togather.util.putPref
import com.tmdhoon.togather.viewmodel.DetailViewModel


class DetailFragment : BottomSheetDialogFragment() {

    private val detailViewModel: DetailViewModel by lazy {
        DetailViewModel()
    }

    private val tagList: ArrayList<Tags> by lazy {
        ArrayList()
    }

    private val pref: SharedPreferences by lazy {
        initPref(requireContext(), MODE_PRIVATE)
    }

    private var postId: Int = 0

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        return dialog
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.lifecycleOwner = this
        binding.detailViewModel = detailViewModel
        binding.detailFragment = this
        observeDetailResponse()
        observeLikeOnResponse()
        observeLikeOffResponse()
        getPostDetail()

        return binding.root
    }

    private fun getPostDetail() {
        detailViewModel.getPosts(
            getPref(
                initPref(requireContext(), MODE_PRIVATE),
                "postId",
                0
            ) as Int
        )
    }

    fun editPost() {
        putPref(
            editor = pref.edit(),
            key = "isEdited",
            true
        )
        PostFragment().show(
            parentFragmentManager,
            PostFragment().tag,
        )
        putPref(
            pref.edit(),
            "title",
            binding.tvDetailTitle.text,
        )
        putPref(
            pref.edit(),
            "content",
            binding.tvDetailContent.text,
        )

    }

    fun like() {
        if (getPref(pref, "like$postId", false) as Boolean) {
            detailViewModel.unLike(postId)
        } else {
            detailViewModel.like(postId)
        }
    }

    private fun observeLikeOnResponse() {
        detailViewModel.likeOnResponse.observe(viewLifecycleOwner) {
            when (it.code()) {
                204 -> {
                    setLikeOn()
                    putPref(pref.edit(), "like$postId", true)
                    detailViewModel.getPosts(postId)
                }
            }
        }
    }

    private fun observeLikeOffResponse() {
        detailViewModel.likeOffResponse.observe(viewLifecycleOwner) {
            when (it.code()) {
                204 -> {
                    setLikeOff()
                    putPref(pref.edit(), "like$postId", false)
                    detailViewModel.getPosts(postId)
                }
            }
        }
    }

    private fun setLikeOn() {
        binding.imgDetailLike.setImageDrawable(
            getDrawable(
                requireContext(),
                R.drawable.ic_detail_like_on,
            )
        )
    }

    private fun setLikeOff() {
        binding.imgDetailLike.setImageDrawable(
            getDrawable(
                requireContext(),
                R.drawable.ic_detail_like_off
            )
        )
    }

    private fun observeDetailResponse() {
        detailViewModel.detailResponse.observe(viewLifecycleOwner) {
            when (it.code()) {
                200 -> {
                    postId = getPref(pref, "postId", 0) as Int
                    tagList.clear()
                    tagList.addAll(it.body()!!.tags)
                    binding.rvDetailTag.run {
                        adapter = MainTagListAdapter(tagList)
                        layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                    }
                    if (it.body()!!.is_liked) {
                        setLikeOn()
                        putPref(pref.edit(), "like$postId", false)

                    } else {
                        setLikeOff()
                        putPref(pref.edit(), "like$postId", false)
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