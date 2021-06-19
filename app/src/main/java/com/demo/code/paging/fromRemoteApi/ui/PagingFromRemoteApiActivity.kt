package com.demo.code.paging.fromRemoteApi.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.demo.code.R
import com.demo.code.base.BaseActivity
import com.demo.code.databinding.ActivityPagingFromRemoteApiBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PagingFromRemoteApiActivity : BaseActivity() {

    private lateinit var binding: ActivityPagingFromRemoteApiBinding

    private val redditAdapter = RedditAdapter()
    private val redditViewModel: RedditViewModel by lazy {
        ViewModelProvider(this).get(RedditViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityPagingFromRemoteApiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        fetchPosts()
    }

    private fun fetchPosts() {
        lifecycleScope.launch {
            redditViewModel.fetchPosts().collectLatest { pagingData ->
                redditAdapter.submitData(pagingData)
            }
        }

    }

    private fun setupViews() {
        binding.apply {
            rvPosts.adapter = redditAdapter
            rvPosts.adapter = redditAdapter.withLoadStateHeaderAndFooter(
                header = RedditLoadingAdapter { redditAdapter.retry() },
                footer = RedditLoadingAdapter { redditAdapter.retry() }
            )
        }
    }
}