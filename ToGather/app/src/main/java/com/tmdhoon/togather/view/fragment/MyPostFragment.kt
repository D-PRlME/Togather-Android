package com.tmdhoon.togather.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentMyPostBinding
import com.tmdhoon.togather.dto.response.MyPostList
import com.tmdhoon.togather.remote.MyPostsAdapter
import com.tmdhoon.togather.viewmodel.MyInfoViewModel

class MyPostFragment : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentMyPostBinding

    private val myInfoViewModel : MyInfoViewModel by lazy {
        ViewModelProvider(this).get(MyInfoViewModel::class.java)
    }

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

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_post, container, false)
        observeEvent()
        myInfoViewModel.getMyPostsList()
        return binding.root
    }

    private fun observeEvent(){
        myInfoViewModel.myPostsResponse.observe(viewLifecycleOwner){
            when(it.code()){
                200 ->{
                    initializeRecyclerView(it.body()!!.post_list)
                }
            }
        }
    }

    private fun initializeRecyclerView(myPostList : ArrayList<MyPostList>) {
        binding.rvMyPostList.run {
            adapter = MyPostsAdapter(myPostList, requireContext(), parentFragmentManager)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}