package com.demo.code.workmanager.chainingworker

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.demo.code.base.BaseActivity
import com.demo.code.databinding.ActivityChainWorkerBinding
import com.demo.code.workmanager.chainingworker.workers.DownloadWorker
import com.demo.code.workmanager.chainingworker.workers.FileClearWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChainingWorkerActivity : BaseActivity() {

    private lateinit var binding: ActivityChainWorkerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChainWorkerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        downloadImage()
    }


    private fun downloadImage() {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .build()

        val clearFilesWorker = OneTimeWorkRequestBuilder<FileClearWorker>()
            .build()

        val downloadRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
            .setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance(this)
        workManager.beginWith(clearFilesWorker)
            .then(downloadRequest)
            .enqueue()

        workManager.getWorkInfoByIdLiveData(downloadRequest.id).observe(this, Observer { info ->
            if (info.state.isFinished) {
                val imagePath = info.outputData.getString("image_path")

                if (!imagePath.isNullOrEmpty()) {
                    displayImage(imagePath)
                }
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