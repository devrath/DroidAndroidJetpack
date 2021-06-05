package com.demo.code.workmanager.exampleone

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.demo.code.base.BaseActivity
import com.demo.code.databinding.ActivityWorkManagerExampleOneBinding
import com.demo.code.workmanager.exampleone.workers.*

class WorkManagerExampleOneActivity : BaseActivity() {

    private lateinit var binding: ActivityWorkManagerExampleOneBinding

    companion object {
        private const val GALLERY_REQUEST_CODE = 300
        private const val PERMISSIONS_REQUEST_CODE = 301

        private const val MAX_NUMBER_REQUEST_PERMISSIONS = 2

        private const val IMAGE_TYPE = "image/*"
        private const val IMAGE_CHOOSER_TITLE = "Select Picture"

        private val PERMISSIONS = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    private var permissionRequestCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkManagerExampleOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()

        requestPermissionsIfNecessary()
    }

    private fun initUi() {
        binding.uploadGroup.visibility = View.GONE
        binding.pickPhotosButton.setOnClickListener { showPhotoPicker() }
    }

    private fun showPhotoPicker() {
        val intent = Intent().apply {
            type = IMAGE_TYPE
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            action = Intent.ACTION_OPEN_DOCUMENT
        }

        startActivityForResult(Intent.createChooser(intent, IMAGE_CHOOSER_TITLE), GALLERY_REQUEST_CODE)
    }

    private fun requestPermissionsIfNecessary() {
        if (!hasRequiredPermissions()) {
            askForPermissions()
        }
    }

    private fun askForPermissions() {
        if (permissionRequestCount < MAX_NUMBER_REQUEST_PERMISSIONS) {
            permissionRequestCount += 1

            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSIONS_REQUEST_CODE)
        } else {
            binding.pickPhotosButton.isEnabled = false
        }
    }

    private fun hasRequiredPermissions(): Boolean {
        val permissionResults = PERMISSIONS.map { permission ->
            ContextCompat.checkSelfPermission(this, permission) == PERMISSION_GRANTED
        }

        return permissionResults.all { isGranted -> isGranted }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            requestPermissionsIfNecessary()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null && resultCode == Activity.RESULT_OK && requestCode == GALLERY_REQUEST_CODE) {

            val applySepiaFilter = buildSepiaFilterRequests(data)
            val zipFiles = OneTimeWorkRequest.Builder(CompressWorker::class.java).build()

            val uploadZip = OneTimeWorkRequest.Builder(UploadWorker::class.java)
                .build()

            val cleanFiles = OneTimeWorkRequest.Builder(CleanFilesWorker::class.java).build()

            val workManager = WorkManager.getInstance()

            workManager.beginWith(cleanFiles)
                .then(applySepiaFilter)
                .then(zipFiles)
                .then(uploadZip)
                .enqueue()
        }
    }

    private fun buildSepiaFilterRequests(intent: Intent): List<OneTimeWorkRequest> {
        val filterRequests = mutableListOf<OneTimeWorkRequest>()

        intent.clipData?.run {
            for (i in 0 until itemCount) {
                val imageUri = getItemAt(i).uri

                val filterRequest = OneTimeWorkRequest.Builder(FilterWorker::class.java)
                    .setInputData(buildInputDataForFilter(imageUri, i))
                    .build()
                filterRequests.add(filterRequest)
            }
        }


        intent.data?.run {
            val filterWorkRequest = OneTimeWorkRequest.Builder(FilterWorker::class.java)
                .setInputData(buildInputDataForFilter(this, 0))
                .build()

            filterRequests.add(filterWorkRequest)
        }

        return filterRequests
    }

    private fun buildInputDataForFilter(imageUri: Uri?, index: Int): Data {
        val builder = Data.Builder()
        if (imageUri != null) {
            builder.putString(KEY_IMAGE_URI, imageUri.toString())
            builder.putInt(KEY_IMAGE_INDEX, index)
        }
        return builder.build()
    }
}