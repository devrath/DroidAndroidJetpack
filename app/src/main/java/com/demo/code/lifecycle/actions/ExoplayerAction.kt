package com.demo.code.lifecycle.actions

import android.net.Uri
import com.google.android.exoplayer2.SimpleExoPlayer

sealed class ExoplayerAction {
    data class  BindExoplayer(val simpleExoplayer: SimpleExoPlayer) : ExoplayerAction()
    data class  ProgressBarVisibility(val isVisible: Boolean) : ExoplayerAction()
}

