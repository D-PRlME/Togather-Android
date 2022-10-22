package com.tmdhoon.togather.remote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ItemMainTagBinding
import com.tmdhoon.togather.dto.response.data.Tags

class MainTagAdapter(private val arrayList : ArrayList<Tags>) :
    RecyclerView.Adapter<MainTagAdapter.MainTagViewHolder>() {

    class MainTagViewHolder(val binding : ItemMainTagBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(arrayList : Tags){
            binding.tag = arrayList
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTagViewHolder {
        val binding = DataBindingUtil.inflate<ItemMainTagBinding>(LayoutInflater.from(parent.context), R.layout.item_main_tag, parent, false)
        return MainTagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainTagViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}