package com.demo.code.activities

import android.os.Bundle
import android.view.View
import com.demo.code.R
import com.demo.code.base.BaseActivity
import com.demo.code.databinding.ActivitySelectionBinding
import com.demo.code.utils.extensions.openActivity

class SelectionScreenActivity : BaseActivity() , View.OnClickListener {

    private lateinit var binding: ActivitySelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListener()
    }

    private fun setClickListener() {
        binding.navDrawerActivity.setOnClickListener(this)
        binding.navTwoContainerActivity.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.navDrawerActivity -> openActivity(NavigationDrawerActivity::class.java)
            R.id.navTwoContainerActivity -> openActivity(TwoFragmentContainerActivity::class.java)
        }
    }
}