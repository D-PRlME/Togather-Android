package com.tmdhoon.togather.view.fragment

import android.os.Bundle
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

    private val tagList : ArrayList<Tags> by lazy {
        ArrayList()
    }

    private val mainViewModel : MainViewModel by lazy {
        MainViewModel()
    }

    private val mainTagListAdapter : MainTagListAdapter by lazy {
        MainTagListAdapter(tagList)
    }

    private val mainAdapter : MainAdapter by lazy {
        MainAdapter(postList, mainTagListAdapter, this.requireContext(), parentFragmentManager)
    }

    private val mainTagAdapter : MainTagAdapter by lazy {
        MainTagAdapter(tags)
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initDataBinding(inflater, container)
        initRequest()
        initObserve()
        initRecyclerView()

        return binding.root
    }

    private fun initObserve() {
        mainViewModel.mainResponse.observe(viewLifecycleOwner, Observer {
            when(it.code()){
                200 ->{
                    postList.clear()
                    postList.addAll(it.body()!!.post_list)
                    initRecyclerView()
                }
            }
        })

        mainViewModel.tagResponse.observe(viewLifecycleOwner, Observer {
            when(it.code()){
                SUCCESS ->{
                    tags.clear()
                    tags.addAll(it.body()!!.tags)
                    initRecyclerView()
                }
            }
        })
    }

    private fun initRequest() {
        mainViewModel.tag()
        mainViewModel.get()
    }

    private fun initDataBinding(inflater : LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.tagInfo = mainViewModel
        binding.lifecycleOwner = this
    }

    private fun initRecyclerView() {
        binding.rvHomeTag.adapter = mainTagAdapter
        binding.rvHomeTag.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvHomeRecyclerView.adapter = mainAdapter
        binding.rvHomeRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
    }
}