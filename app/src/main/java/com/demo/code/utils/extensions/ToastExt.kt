package com.demo.code.utils.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

private var toast: Toast? = null

@SuppressLint("ShowToast")
fun toast(msg: Any?, isShort: Boolean = true, context : Context) {
    msg?.let {
        if (toast == null) {
            toast = Toast.makeText(context, msg.toString(), Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(msg.toString())
        }
        toast!!.duration = if (isShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
        toast!!.show()
    }
}
