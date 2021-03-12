package com.demo.code.utils.extensions

import android.app.Activity
import android.util.DisplayMetrics





fun Activity.isInPortrait(): Boolean {
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.heightPixels > displayMetrics.widthPixels
}