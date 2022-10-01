package com.tmdhoon.togather.remote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ItemTagListBinding
import com.tmdhoon.togather.model.response.data.Tags

class TagAdapter(private val tagList : ArrayList<Tags>) :
    RecyclerView.Adapter<TagAdapter.TagViewHolder>() {
    class TagViewHolder(val binding : ItemTagListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tag : Tags){
            binding.tagInfo = tag
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val binding = DataBindingUtil.inflate<ItemTagListBinding>(LayoutInflater.from(parent.context), R.layout.item_tag_list, parent, false)
        return TagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(tagList.get(position))
    }

    override fun getItemCount(): Int {
        return tagList.size
    }

}