package com.demo.code.paging.fromRemoteApi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.code.R
import com.demo.code.paging.fromRemoteApi.models.FeedPost
import com.demo.code.paging.fromRemoteApi.utils.DiffUtilCallBack
import kotlinx.android.synthetic.main.adapter_row.view.*

class RemoteApiAdapter :
    PagingDataAdapter<FeedPost, RemoteApiAdapter.RedditViewHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_row, parent, false)
        return RedditViewHolder(view)
    }

    override fun onBindViewHolder(holder: RedditViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
    }

    class RedditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText: TextView = itemView.title

        fun bindPost(feedPost: FeedPost) {
            with(feedPost) {
                titleText.text = title
            }
        }
    }
}