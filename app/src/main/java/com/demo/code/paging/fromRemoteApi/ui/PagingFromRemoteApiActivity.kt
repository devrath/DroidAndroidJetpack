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

    // Binding: View references
    private lateinit var binding: ActivityPagingFromRemoteApiBinding

    // Adapter: List of items
    private val adapter = RemoteApiAdapter()

    // viewModel reference
    private val remoteApiViewModel: RemoteApiViewModel by lazy {
        ViewModelProvider(this).get(RemoteApiViewModel.thisClass)
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

            remoteApiViewModel.fetchPosts().collectLatest { pagingData ->
                // We get the new data from the flow - We publish the new data to adapter
                adapter.submitData(pagingData)
            }
        }
    }

    private fun setupViews() {
        binding.apply {
            // Adapter: List of items
            rvPosts.adapter = adapter
            // Adapters: Header and Footer item
            rvPosts.adapter = adapter.withLoadStateHeaderAndFooter(
                // Header
                header = RemoteApiLoadingAdapter { adapter.retry() },
                // Footer
                footer = RemoteApiLoadingAdapter { adapter.retry() }
            )
        }
    }
}