package com.demo.code.paging.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.code.R
import com.demo.code.databinding.ListItemBinding
import com.demo.code.paging.models.Data

class MainListAdapter : PagingDataAdapter<Data, MainListAdapter.HoursViewHolder>(DataDifferntiator) {

    override fun onBindViewHolder(holder: HoursViewHolder, position: Int) {

        holder.binding.textViewName.text =
        "${getItem(position)?.firstName} ${getItem(position)?.lastName}"

        holder.binding.textViewEmail.text = getItem(position)?.email
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursViewHolder {
        val binding = ListItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return HoursViewHolder(binding)
    }

    object DataDifferntiator : DiffUtil.ItemCallback<Data>() {

        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    inner class HoursViewHolder(val binding: ListItemBinding) :RecyclerView.ViewHolder(binding.root)

}
