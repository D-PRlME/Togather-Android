package com.tmdhoon.togather.remote

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ItemMainTagBinding
import com.tmdhoon.togather.dto.response.data.Tags
import com.tmdhoon.togather.view.MainActivity
import com.tmdhoon.togather.view.fragment.SearchFragment
import com.tmdhoon.togather.viewmodel.PostViewModel

class MainTagAdapter(
    private val arrayList : ArrayList<Tags>,
    private val postViewModel : PostViewModel,
    private val context : Context,
    ) :
    RecyclerView.Adapter<MainTagAdapter.MainTagViewHolder>() {

    class MainTagViewHolder(val binding : ItemMainTagBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(arrayList : Tags){
            binding.tags = arrayList
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTagViewHolder {
        val binding = DataBindingUtil.inflate<ItemMainTagBinding>(LayoutInflater.from(parent.context), R.layout.item_main_tag, parent, false)
        return MainTagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainTagViewHolder, position: Int) {
        holder.bind(arrayList[position])
        holder.itemView.setOnClickListener {
            postViewModel.saveTag(arrayList[position].name)
            val activity = context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().replace(R.id.frameLayout, SearchFragment()).commitNow()
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}