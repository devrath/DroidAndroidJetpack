package com.demo.code.utils.extensions

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.fragment.app.Fragment

inline fun <reified T : Activity> Fragment.goActivity() = startActivity(Intent(activity, T::class.java))

inline fun <reified T : Activity> Fragment.goActivity(requestCode: Int) = startActivityForResult(Intent(activity, T::class.java), requestCode)

inline fun <reified T : Service> Fragment.goService() = activity?.startService(Intent(activity, T::class.java))

inline fun <reified T : Service> Fragment.goService(sc: ServiceConnection, flags: Int = Context.BIND_AUTO_CREATE) = activity?.bindService(Intent(activity, T::class.java), sc, flags)

fun Fragment.finish() = activity?.finish()

