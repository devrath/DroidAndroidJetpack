package com.demo.code.lifecycle.activities

import android.os.Bundle
import android.view.View
import com.demo.code.base.BaseActivity
import com.demo.code.databinding.ActivityPlayerBinding
import com.demo.code.lifecycle.actions.ExoplayerAction
import com.demo.code.lifecycle.util.ExoplayerActivityObserver
import com.google.android.exoplayer2.Player


class ExoplayerActivity : BaseActivity(), Player.EventListener {

    private lateinit var binding: ActivityPlayerBinding

    private lateinit var locationListener: ExoplayerActivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initExoplayerListener()
    }

    private fun initExoplayerListener() {
        locationListener = ExoplayerActivityObserver(lifecycle,this) { it ->
            when(it) {
                is ExoplayerAction.BindExoplayer -> binding.exoplayerView.player = it.simpleExoplayer
                is ExoplayerAction.ProgressBarVisibility -> handleProgressVisibilityOfPlayer(it.isVisible)
            }
        }
    }

    private fun handleProgressVisibilityOfPlayer(visible: Boolean) {
        if (visible)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.INVISIBLE
    }

}