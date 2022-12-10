package com.tmdhoon.togather.view.fragment

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentSearchBinding
import com.tmdhoon.togather.remoteimport.MainAdapter
import com.tmdhoon.togather.util.createTagFragment
import com.tmdhoon.togather.util.hideKeyBoard
import com.tmdhoon.togather.util.initPref
import com.tmdhoon.togather.viewmodel.MainViewModel
import com.tmdhoon.togather.viewmodel.PostViewModel
import com.tmdhoon.togather.viewmodel.SearchViewModel

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private val searchViewModel: SearchViewModel by lazy {
        SearchViewModel()
    }

    private val mainViewModel : MainViewModel by lazy {
        MainViewModel()
    }

    private val postViewModel : PostViewModel by lazy {
        PostViewModel(initPref(
            context = requireContext(),
            mode = MODE_PRIVATE
        ))
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
                searchViewModel.searchPostTitle(
                    title = binding.etSearchSearch.text.toString(),
                )
                isRefreshing = false
            }
        }
    }

    fun searchTitle() {
        if (binding.etSearchSearch.text.isNotEmpty()) {
            hideKeyBoard(requireContext(), binding.root)
            searchViewModel.searchPostTitle(
                title = binding.etSearchSearch.text.toString(),
            )
        }
    }

    private fun observeSearchTitleResponse() {
        searchViewModel.searchPostTitleResponse.observe(viewLifecycleOwner) {
            when (it.code()) {
                200 -> {
                    binding.rvSearch.run {
                        adapter = MainAdapter(
                            postList = it.body()!!.post_list,
                            context = requireContext(),
                            parentFragmentManager = parentFragmentManager,
                        )
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                }
            }
        }
    }
}