package com.tmdhoon.togather.remote

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ListMyPostBinding
import com.tmdhoon.togather.dto.response.MyPostList
import com.tmdhoon.togather.dto.response.data.User
import com.tmdhoon.togather.view.fragment.DetailFragment

class MyPostsAdapter(
    private val myPostList : ArrayList<MyPostList>,
    private val context : Context,
    private val fragmentManager: FragmentManager,
) : RecyclerView.Adapter<MyPostsAdapter.MyPostViewHolder>() {

    class MyPostViewHolder(
        val binding : ListMyPostBinding,
        ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            myPostList : MyPostList,
            userResponse : User
        ){
            binding.run {
                main = myPostList
                user = userResponse
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyPostViewHolder =
        MyPostViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_my_post, parent,
                false,
            )
        )


    override fun onBindViewHolder(
        holder: MyPostViewHolder,
        position: Int
    ) {
        holder.run {
            bind(
                myPostList[position],
                myPostList[position].user,
            )
            binding.rvMainTagList.run {
                adapter = MainTagListAdapter(
                    myPostList[position].tags,
                )
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false,
                )
            }
            itemView.setOnClickListener {
                DetailFragment().show(fragmentManager, DetailFragment().tag)
            }
        }
    }

    override fun getItemCount(): Int =
        myPostList.size
}