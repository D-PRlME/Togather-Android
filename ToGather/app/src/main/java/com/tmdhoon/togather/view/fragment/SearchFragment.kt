package com.tmdhoon.togather.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentSearchBinding
import com.tmdhoon.togather.remote.SearchTagAdapter
import com.tmdhoon.togather.remoteimport.MainAdapter
import com.tmdhoon.togather.util.createTagFragment
import com.tmdhoon.togather.util.hideKeyBoard
import com.tmdhoon.togather.util.initPref
import com.tmdhoon.togather.util.putPref
import com.tmdhoon.togather.viewmodel.MainViewModel
import com.tmdhoon.togather.viewmodel.PostViewModel
import com.tmdhoon.togather.viewmodel.PostViewModelFactory
import com.tmdhoon.togather.viewmodel.SearchViewModel

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private val searchViewModel: SearchViewModel by lazy {
        SearchViewModel()
    }

    private val mainViewModel: MainViewModel by lazy {
        MainViewModel()
    }

    private val postViewModelFactory by lazy {
        PostViewModelFactory(requireContext())
    }

    private val postViewModel by lazy {
        ViewModelProvider(this, postViewModelFactory)[PostViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search,
            container,
            false,
        )
        binding.lifecycleOwner = this
        binding.searchFragment = this
        refresh()
        observeSearchTitleResponse()
        initAllTagButton()
        initRecyclerView()
        Log.d("TEST", postViewModel.getTag().toString())

        return binding.root
    }

    private fun initAllTagButton() {
        binding.btSearchTag.setOnClickListener {
            createTagFragment(
                context = requireContext(),
                mainViewModel = mainViewModel,
                viewLifecycleOwner = viewLifecycleOwner,
                postViewModel = postViewModel,
            )
        }
    }

    private fun refresh() {
        binding.slSearchRefresh.run {
            setOnRefreshListener {
                isRefreshing = false
            }
        }
    }

    fun searchTitle() {
        if (postViewModel.roadTag().isEmpty()) {
            if (binding.etSearchSearch.text.isNotEmpty()) {
                hideKeyBoard(requireContext(), binding.root)
                searchViewModel.searchPostTitle(
                    title = binding.etSearchSearch.text.toString(),
                )
            }
        }else{
            searchViewModel.searchPostTag(postViewModel.roadTag())
        }
    }

    private fun observeSearchTitleResponse() {
        searchViewModel.searchPostTitleResponse.observe(viewLifecycleOwner) {
            when (it.code()) {
                200 -> {
                    val linearLayoutManager = LinearLayoutManager(requireContext())
                    linearLayoutManager.run {
                        reverseLayout = true
                        stackFromEnd = true
                    }
                    binding.rvSearch.run {
                        adapter = MainAdapter(
                            postList = it.body()!!.post_list,
                            context = requireContext(),
                            parentFragmentManager = parentFragmentManager,
                        )
                        layoutManager = linearLayoutManager
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        Log.d("TEST", arrayListOf(postViewModel.roadTag()).toString())
        if (arrayListOf(postViewModel.roadTag()).size != 0) {
            binding.rvSearchTag.run {
                visibility = View.VISIBLE
                adapter = SearchTagAdapter(arrayListOf(postViewModel.roadTag()), postViewModel)
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        initPref(requireContext()).edit().remove("tag")
    }
}