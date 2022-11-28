package com.tmdhoon.togather.remoteimport

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tmdhoon.togather.remote.ItemDecoration
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ItemMainListBinding
import com.tmdhoon.togather.dto.response.data.PostList
import com.tmdhoon.togather.dto.response.data.User
import com.tmdhoon.togather.remote.MainTagListAdapter
import com.tmdhoon.togather.util.initPref
import com.tmdhoon.togather.util.putPref
import com.tmdhoon.togather.util.startIntent
import com.tmdhoon.togather.view.DetailUserActivity
import com.tmdhoon.togather.view.fragment.DetailFragment

class MainAdapter (
    val postList: ArrayList<PostList>,
    val context : Context,
    val parentFragmentManager : FragmentManager) :
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

        holder.itemView.setOnClickListener {
            putPref(
                initPref(
                    context = context,
                    mode = MODE_PRIVATE,
                ).edit(),
                "postId",
                postList[position].post_id,
            )
            putPref(
                editor = initPref(context, MODE_PRIVATE).edit(),
                key = "userId",
                value = postList[position].user.user_id
            )
            DetailFragment().show(parentFragmentManager, DetailFragment().tag)
        }

        holder.binding.rvMainTagList.run {
            adapter = MainTagListAdapter(postList[position].tags)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        holder.binding.clMainListProfile.setOnClickListener {
            this.context.startActivity(
                Intent(this.context, DetailUserActivity::class.java)
                .putExtra("userId", postList[position].user.user_id)
            )
        }
    }

    override fun getItemCount(): Int =
        postList.size

}