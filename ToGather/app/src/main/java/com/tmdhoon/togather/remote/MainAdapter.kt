package com.tmdhoon.togather.remoteimport

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ItemMainListBinding
import com.tmdhoon.togather.model.response.data.PostList
import com.tmdhoon.togather.model.response.data.User

class MainAdapter (val postList: ArrayList<PostList>) :
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
    }

    override fun getItemCount(): Int {
        return postList.size
    }

}