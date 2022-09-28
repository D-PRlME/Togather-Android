package com.tmdhoon.togather.view.fragment

import android.os.Bundle
import android.provider.ContactsContract.Data
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
import com.tmdhoon.togather.model.data.PostList
import com.tmdhoon.togather.model.data.Test
import com.tmdhoon.togather.model.response.MainResponse
import com.tmdhoon.togather.network.ApiProvider
import com.tmdhoon.togather.remote.MainAdapter
import com.tmdhoon.togather.util.ACCESS_TOKEN
import com.tmdhoon.togather.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    companion object{
        fun newInstance() : HomeFragment{
            return HomeFragment()
        }
    }

    private lateinit var binding : FragmentHomeBinding

    private val arrayList : ArrayList<MainResponse> by lazy {
        ArrayList()
    }

    private val mainViewModel : MainViewModel by lazy {
        MainViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        mainViewModel.tag()
        mainViewModel.get()

        binding.tagInfo = mainViewModel

        binding.lifecycleOwner = this

        binding.rvHomeRecyclerView.layoutManager = LinearLayoutManager(view?.context)

        binding.rvHomeRecyclerView.adapter = MainAdapter(arrayList)

        return binding.root
    }
}