package com.demo.code.workmanager.exampleone

import android.os.Bundle
import com.demo.code.base.BaseActivity
import com.demo.code.databinding.ActivityWorkManagerExampleOneBinding

class WorkManagerExampleOneActivity : BaseActivity() {

    private lateinit var binding: ActivityWorkManagerExampleOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkManagerExampleOneBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}