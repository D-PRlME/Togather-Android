package com.tmdhoon.togather.remote

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ItemMainTagListBinding
import com.tmdhoon.togather.dto.response.data.TagLists
import com.tmdhoon.togather.dto.response.data.Tags
import com.tmdhoon.togather.util.TAG

class MainTagListAdapter(private val tagList: ArrayList<TagLists>) :
    RecyclerView.Adapter<MainTagListAdapter.MainTagViewHolder>() {

    class MainTagViewHolder(val binding: ItemMainTagListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tags: TagLists) {
            binding.tag = tags
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTagViewHolder {
        val binding = DataBindingUtil.inflate<ItemMainTagListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_main_tag_list,
            parent,
            false
        )

        return MainTagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainTagViewHolder, position: Int) {
        holder.bind(tagList[position])
    }

    override fun getItemCount(): Int {
        Log.d(TAG, tagList.size.toString())
        return tagList.size
    }
}