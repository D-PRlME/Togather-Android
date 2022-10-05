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
import com.tmdhoon.togather.model.response.data.PostList
import com.tmdhoon.togather.remoteimport.MainAdapter
import com.tmdhoon.togather.viewmodel.MainViewModel

class HomeFragment : Fragment() {

    companion object{
        fun newInstance() : HomeFragment{
            return HomeFragment()
        }
    }

    private lateinit var binding : FragmentHomeBinding

    private val postList : ArrayList<PostList> by lazy {
        ArrayList()
    }

    private val mainViewModel : MainViewModel by lazy {
        MainViewModel()
    }

    private val mainAdapter : MainAdapter by lazy {
        MainAdapter(postList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initDataBinding(inflater, container)
        initRequest()
        initObserve()

        return binding.root
    }

    private fun initObserve() {
        mainViewModel.mainResponse.observe(viewLifecycleOwner, Observer {
            when(it.code()){
                200 ->{
                    postList.addAll(it.body()!!.post_list)
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
        binding.rvHomeRecyclerView.adapter = mainAdapter
        binding.rvHomeRecyclerView.layoutManager = LinearLayoutManager(view?.context)
    }
}