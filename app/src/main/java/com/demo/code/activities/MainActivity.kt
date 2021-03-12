package com.demo.code.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.code.R
import com.demo.code.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}