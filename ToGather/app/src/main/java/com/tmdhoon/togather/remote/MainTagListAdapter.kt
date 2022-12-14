package com.tmdhoon.togather.remote

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ItemMainTagListBinding
import com.tmdhoon.togather.dto.response.data.Tags

class MainTagListAdapter(
    private val tagList: ArrayList<Tags>) :
    RecyclerView.Adapter<MainTagListAdapter.MainTagViewHolder>() {

    class MainTagViewHolder(val binding: ItemMainTagListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tags: Tags) {
            binding.tags = tags
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
        return tagList.size
    }
}