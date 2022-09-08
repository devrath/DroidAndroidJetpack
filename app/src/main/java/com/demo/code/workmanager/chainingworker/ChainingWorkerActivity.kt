package com.demo.code.workmanager.simpleworker

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.demo.code.base.BaseActivity
import com.demo.code.databinding.ActivitySimpleWorkerBinding
import com.demo.code.workmanager.simpleworker.workers.SimpleDownloadWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.net.HttpURLConnection
import java.net.URL

class SimpleWorkerActivity : BaseActivity() {

    private lateinit var binding: ActivitySimpleWorkerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleWorkerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        downloadImage()
    }


    private fun downloadImage() {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .build()

        val downloadRequest = OneTimeWorkRequestBuilder<SimpleDownloadWorker>()
            .setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance(this)
        workManager.enqueue(downloadRequest)

        workManager.getWorkInfoByIdLiveData(downloadRequest.id).observe(this, Observer { info ->
            if (info.state.isFinished) {
                val imageFile = File(externalMediaDirs.first(), "owl_image.jpg")
                displayImage(imageFile.absolutePath)
            }
        })
    }

    private fun displayImage(imagePath: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val bitmap = loadImageFromFile(imagePath)
            binding.imgViewId.setImageBitmap(bitmap)
        }
    }

    private suspend fun loadImageFromFile(imagePath: String) = withContext(Dispatchers.IO) {
        BitmapFactory.decodeFile(imagePath)
    }

}