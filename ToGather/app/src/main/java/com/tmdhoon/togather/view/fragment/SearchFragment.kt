package com.tmdhoon.togather.view.fragment

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.renderscript.ScriptGroup.Input
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentHomeBinding
import com.tmdhoon.togather.databinding.FragmentSearchBinding
import com.tmdhoon.togather.remoteimport.MainAdapter
import com.tmdhoon.togather.repository.SearchRepository
import com.tmdhoon.togather.viewmodel.SearchViewModel
import java.lang.reflect.TypeVariable

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private val searchViewModel: SearchViewModel by lazy {
        SearchViewModel()
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
            TagFragment().show(
                parentFragmentManager,
                TagFragment().tag,
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