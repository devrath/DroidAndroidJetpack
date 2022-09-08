package com.demo.code.workmanager

import android.os.Bundle
import com.demo.code.base.BaseActivity
import com.demo.code.databinding.ActivityPagingBinding
import com.demo.code.databinding.ActivityWorkManagerBinding
import com.demo.code.workmanager.chainingworker.ChainingWorkerActivity
import com.demo.code.workmanager.exampleone.WorkManagerExampleOneActivity
import com.demo.code.workmanager.simpleworker.SimpleWorkerActivity
import com.demo.extensions.intent.openActivity

class WorkManagerActivity  : BaseActivity() {

    private lateinit var binding: ActivityWorkManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            this.simpleWorkerId.setOnClickListener {
                openActivity(SimpleWorkerActivity::class.java)
            }
            this.chainingWorkerId.setOnClickListener {
                openActivity(ChainingWorkerActivity::class.java)
            }
            this.workManagerId.setOnClickListener {
                openActivity(WorkManagerExampleOneActivity::class.java)
            }
        }
    }


}