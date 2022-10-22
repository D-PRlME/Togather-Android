package com.tmdhoon.togather.remoteimport

import android.content.Context
import android.util.Log
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tmdhoon.togather.ItemDecoration
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ItemMainListBinding
import com.tmdhoon.togather.dto.response.data.PostList
import com.tmdhoon.togather.dto.response.data.User
import com.tmdhoon.togather.remote.MainTagAdapter

class MainAdapter (val postList: ArrayList<PostList>, val mainTagAdapter: MainTagAdapter, val context : Context) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    class MainViewHolder(val binding : ItemMainListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(postList: PostList, user : User){
            binding.main = postList
            binding.user = user
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = DataBindingUtil.inflate<ItemMainListBinding>(LayoutInflater.from(parent.context), R.layout.item_main_list, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(postList.get(position), postList.get(position).user)

        holder.binding.rvMainTagList.removeItemDecoration(ItemDecoration(20))
        holder.binding.rvMainTagList.adapter = mainTagAdapter
        holder.binding.rvMainTagList.layoutManager = GridLayoutManager(context, 5)
        holder.binding.rvMainTagList.addItemDecoration(ItemDecoration(-120))
    }

    override fun getItemCount(): Int {
        return postList.size
    }

}