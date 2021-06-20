package com.demo.code.paging.usingLocalSource

import java.util.concurrent.Executors

private val BG_EXECUTOR = Executors.newSingleThreadExecutor()

fun bgThread(f : () -> Unit) {
  BG_EXECUTOR.execute(f)
}