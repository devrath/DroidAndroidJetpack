package com.demo.code.paging

import android.os.Bundle
import com.demo.code.base.BaseActivity
import com.demo.code.databinding.ActivityPagingBinding
import com.demo.code.lifecycle.activities.ExoplayerActivity
import com.demo.code.navigation.activities.SelectionScreenActivity
import com.demo.code.paging.fromLocalDatabase.PagingFromLocalDbActivity
import com.demo.code.paging.fromRemoteApi.PagingFromRemoteApiActivity
import com.demo.extensions.intent.openActivity

class Paging3Activity : BaseActivity() {

    private lateinit var binding: ActivityPagingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            this.pagingFromLocalDbId.setOnClickListener {
                openActivity(PagingFromLocalDbActivity::class.java)
            }
            this.pagingFromRemoteApiId.setOnClickListener {
                openActivity(PagingFromRemoteApiActivity::class.java)
            }
        }
    }


}