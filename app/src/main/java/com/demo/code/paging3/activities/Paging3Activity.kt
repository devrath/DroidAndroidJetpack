package com.demo.code.paging3.activities

import android.os.Bundle
import com.demo.code.base.BaseActivity
import com.demo.code.databinding.ActivityPagingBinding

class Paging3Activity : BaseActivity() {

    private lateinit var binding: ActivityPagingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagingBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}