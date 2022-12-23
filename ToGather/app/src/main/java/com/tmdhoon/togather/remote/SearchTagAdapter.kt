package com.tmdhoon.togather.remote

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ItemTagListBinding
import com.tmdhoon.togather.databinding.ListSearchTagBinding
import com.tmdhoon.togather.dto.response.data.Tags
import com.tmdhoon.togather.util.arrayList
import com.tmdhoon.togather.util.selectedList
import com.tmdhoon.togather.view.fragment.PostFragment
import com.tmdhoon.togather.viewmodel.PostViewModel

class SearchTagAdapter(
    private val tagList : ArrayList<String>,
    private val postViewModel : PostViewModel,
) :
    RecyclerView.Adapter<SearchTagAdapter.TagViewHolder>() {
    
    class TagViewHolder(val binding : ListSearchTagBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tag : String){
            binding.tags = tag
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val binding = DataBindingUtil.inflate<ListSearchTagBinding>(LayoutInflater.from(parent.context), R.layout.list_search_tag, parent, false)
        return TagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(tagList[position])
    }

    override fun getItemCount(): Int {
        return tagList.size
    }

}