package com.demo.code.dataStore.activities

import android.os.Bundle
import com.demo.code.base.BaseActivity
import com.demo.code.databinding.ActivityDataStoreBinding

class DataStoreActivity : BaseActivity() {

    private lateinit var binding: ActivityDataStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}