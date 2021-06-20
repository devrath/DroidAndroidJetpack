package com.demo.code.paging.usingRemoteAndLocalSource.utils

import androidx.recyclerview.widget.DiffUtil
import com.demo.code.paging.usingRemoteAndLocalSource.models.FeedPost

class DiffUtilCallBack : DiffUtil.ItemCallback<FeedPost>() {
    override fun areItemsTheSame(oldItem: FeedPost, newItem: FeedPost): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: FeedPost, newItem: FeedPost): Boolean {
        return oldItem.key == newItem.key
                && oldItem.score == newItem.score
                && oldItem.commentCount == newItem.commentCount
    }
}