package com.tmdhoon.togather.remote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ListUserPositionBinding

class PositionAdapter(
    private val positionList: ArrayList<String>?,
) : RecyclerView.Adapter<PositionAdapter.PositionViewHolder>() {

    class PositionViewHolder(val binding: ListUserPositionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            position: String?,
            pos: Int,
        ) {
            binding.position = pos
            binding.model = position
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PositionViewHolder =
        PositionViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_user_position,
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: PositionViewHolder, position: Int) {
        holder.bind(
            pos = position,
            position = positionList?.get(position)
        )
    }

    override fun getItemCount(): Int =
        positionList?.size ?: 0
}