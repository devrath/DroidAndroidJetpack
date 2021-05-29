package com.demo.code.camerax.activities

import android.os.Bundle
import com.demo.code.base.BaseActivity
import com.demo.code.databinding.ActivityCameraXBinding

class CameraxActivity: BaseActivity() {

    private lateinit var binding: ActivityCameraXBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraXBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}