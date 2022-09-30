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
import com.tmdhoon.togather.model.data.PostList
import com.tmdhoon.togather.remoteimport.MainAdapter
import com.tmdhoon.togather.viewmodel.MainViewModel

class HomeFragment : Fragment() {

    companion object{
        fun newInstance() : HomeFragment{
            return HomeFragment()
        }
    }

    private lateinit var binding : FragmentHomeBinding

    private val arrayList : ArrayList<PostList> by lazy {
        ArrayList()
    }

    private val mainViewModel : MainViewModel by lazy {
        MainViewModel()
    }

    private val mainAdapter : MainAdapter by lazy {
        MainAdapter(arrayList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        initRecyclerView()

        mainViewModel.tag()
        mainViewModel.get()

        mainViewModel.mainResponse.observe(viewLifecycleOwner, Observer {
            when(it.code()){
                200 -> arrayList.addAll(listOf(it.body()!!))
            }
        })

        binding.tagInfo = mainViewModel

        binding.lifecycleOwner = this

        return binding.root
    }

    private fun initRecyclerView() {
        binding.rvHomeRecyclerView.layoutManager = LinearLayoutManager(view?.context)
        binding.rvHomeRecyclerView.adapter = mainAdapter
    }
}