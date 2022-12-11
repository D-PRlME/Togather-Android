package com.tmdhoon.togather.remote

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ItemTagListBinding
import com.tmdhoon.togather.dto.response.data.Tags
import com.tmdhoon.togather.util.arrayList
import com.tmdhoon.togather.util.selectedList
import com.tmdhoon.togather.view.fragment.PostFragment
import com.tmdhoon.togather.viewmodel.PostViewModel

class TagAdapter(
    private val tagList : ArrayList<Tags>,
    private val postViewModel : PostViewModel,
) :
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
        holder.bind(tagList[position])
        if(selectedList[position]){
            holder.itemView.setBackgroundColor(Color.GRAY)
        }
        holder.itemView.setOnClickListener {
            if(selectedList[position]){
                it.setBackgroundColor(Color.WHITE)
                postViewModel.removeTag(tagList[position].name.uppercase())

            }else{
                it.setBackgroundColor(Color.GRAY)
                postViewModel.addTag(tagList[position].name.replace('.', '_').uppercase())
            }
            selectedList[position] = !selectedList[position]
        }
    }

    override fun getItemCount(): Int {
        return tagList.size
    }

}