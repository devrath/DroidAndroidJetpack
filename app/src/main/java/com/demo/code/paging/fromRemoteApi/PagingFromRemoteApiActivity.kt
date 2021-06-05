package com.demo.code.paging.fromRemoteApi

import android.os.Bundle
import com.demo.code.base.BaseActivity
import com.demo.code.databinding.ActivityPagingFromRemoteApiBinding

class PagingFromRemoteApiActivity : BaseActivity() {

    private lateinit var binding: ActivityPagingFromRemoteApiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagingFromRemoteApiBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}