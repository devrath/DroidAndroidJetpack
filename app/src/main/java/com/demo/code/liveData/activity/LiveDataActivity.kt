package com.demo.code.liveData.activity

import android.os.Bundle
import com.demo.code.base.BaseActivity
import com.demo.code.databinding.ActivityLiveDataLayoutBinding

class LiveDataActivity : BaseActivity() {

    private lateinit var binding: ActivityLiveDataLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}