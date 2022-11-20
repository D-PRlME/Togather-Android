package com.tmdhoon.togather.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentHomeBinding
import com.tmdhoon.togather.dto.response.data.PostList
import com.tmdhoon.togather.dto.response.data.Tags
import com.tmdhoon.togather.remote.MainTagAdapter
import com.tmdhoon.togather.remote.MainTagListAdapter
import com.tmdhoon.togather.remoteimport.MainAdapter
import com.tmdhoon.togather.util.SUCCESS
import com.tmdhoon.togather.viewmodel.MainViewModel

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private val postList : ArrayList<PostList> by lazy {
        ArrayList()
    }

    private val tags : ArrayList<Tags> by lazy {
        ArrayList()
    }

    private val mainViewModel : MainViewModel by lazy {
        MainViewModel()
    }

    private val mainAdapter : MainAdapter by lazy {
        MainAdapter(
            postList = postList,
            context = this.requireContext(),
            parentFragmentManager = parentFragmentManager,
        )
    }

    private val mainTagAdapter : MainTagAdapter by lazy {
        MainTagAdapter(tags)
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.tagInfo = mainViewModel
        binding.lifecycleOwner = this

        initRequest()
        initObserve()
        initRecyclerView()
        initializeRefreshLayout()

        return binding.root
    }

    private fun initializeRefreshLayout(){
        binding.slHome.run {
            setOnRefreshListener {
                initRequest()
                isRefreshing = false
            }
        }
    }

    private fun initObserve() {
        mainViewModel.mainResponse.observe(viewLifecycleOwner){
            when(it.code()){
                200 ->{
                    postList.run {
                        clear()
                        addAll(it.body()!!.post_list)
                    }
                    initRecyclerView()
                }
            }
        }

        mainViewModel.tagResponse.observe(viewLifecycleOwner) {
            when(it.code()){
                SUCCESS ->{
                    tags.run {
                        clear()
                        addAll(it.body()!!.tags)
                    }
                    initRecyclerView()
                }
            }
        }
    }

    private fun initRequest() {
        mainViewModel.run {
            tag()
            get()
        }
    }

    private fun initRecyclerView() {
        binding.run {
            rvHomeTag.run {
                adapter = mainTagAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false,
                )
            }
            rvHomeRecyclerView.run {
                adapter = mainTagAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
}