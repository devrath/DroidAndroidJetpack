package com.demo.code.workmanager.exampleone

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.util.Log
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.*
import java.util.*
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import kotlin.math.min


object ImageUtils {

  private const val LOG_TAG = "ImageUtils"
//  private const val SERVER_UPLOAD_PATH = "http://10.0.2.2:3000/files" //local server URL
  private const val SERVER_UPLOAD_PATH = "https://simple-file-server.herokuapp.com/files" //shared service URL

  //grayscale multipliers
  private const val GRAYSCALE_RED = 0.3
  private const val GRAYSCALE_GREEN = 0.59
  private const val GRAYSCALE_BLUE = 0.11

  private const val MAX_COLOR = 255

  private const val SEPIA_TONE_RED = 110
  private const val SEPIA_TONE_GREEN = 65
  private const val SEPIA_TONE_BLUE = 20

  private const val DIRECTORY_OUTPUTS = "outputs"
  private const val COMPRESS_BUFFER_CHUNK = 1024

  private val okHttpClient by lazy { OkHttpClient() }

  private const val MULTIPART_NAME = "file"

  /**
   * Sepia filter.
   * From: https://github.com/yaa110/Effects-Pro/blob/master/src/org/appsroid/fxpro/bitmap/BitmapProcessing.java
   */
  fun applySepiaFilter(bitmap: Bitmap): Bitmap {
    // image size
    val width = bitmap.width
    val height = bitmap.height

    // create output bitmap
    val outputBitmap = Bitmap.createBitmap(width, height, bitmap.config)

    // color information
    var alpha: Int
    var red: Int
    var green: Int
    var blue: Int
    var currentPixel: Int

    // scan through all pixels
    for (x in 0 until width) {
      for (y in 0 until height) {

        // get pixel color
        currentPixel = bitmap.getPixel(x, y)

        // get color on each channel
        alpha = Color.alpha(currentPixel)
        red = Color.red(currentPixel)
        green = Color.green(currentPixel)
        blue = Color.blue(currentPixel)

        // apply grayscale sample
        red = (GRAYSCALE_RED * red + GRAYSCALE_GREEN * green + GRAYSCALE_BLUE * blue).toInt()
        green = red
        blue = green

        // apply intensity level for sepid-toning on each channel
        red += SEPIA_TONE_RED
        green += SEPIA_TONE_GREEN
        blue += SEPIA_TONE_BLUE

        //if you overflow any color, set it to MAX (255)
        red = min(red, MAX_COLOR)
        green = min(green, MAX_COLOR)
        blue = min(blue, MAX_COLOR)

        outputBitmap.setPixel(x, y, Color.argb(alpha, red, green, blue))
      }
    }

    bitmap.recycle()

    return outputBitmap
  }

  fun writeBitmapToFile(applicationContext: Context, bitmap: Bitmap): File {
    val randomId = UUID.randomUUID().toString()
    val name = "$randomId.png"

    val outputDirectory = getOutputDirectory(applicationContext)
    val outputFile = File(outputDirectory, name)

    try {
      FileOutputStream(outputFile).use { outputStream ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /* ignored for PNG */, outputStream)
      }
    } catch (e: FileNotFoundException) {
      e.printStackTrace()
    }

    return outputFile
  }

  private fun getOutputDirectory(applicationContext: Context): File {
    return File(applicationContext.filesDir, DIRECTORY_OUTPUTS).apply {
      if (!exists()) {
        mkdirs()
      }
    }
  }

  fun cleanFiles(applicationContext: Context) {
    val outputDirectory = getOutputDirectory(applicationContext)

    outputDirectory.listFiles()?.forEach { it.delete() }
  }

  fun createZipFile(applicationContext: Context, files: Array<String>): Uri {
    val randomId = UUID.randomUUID().toString()
    val name = "$randomId.zip"

    val outputDirectory = getOutputDirectory(applicationContext)
    val outputFile = File(outputDirectory, name)

    val zipOutputStream = ZipOutputStream(BufferedOutputStream(FileOutputStream(outputFile)))
    compressFiles(zipOutputStream, files)

    return Uri.fromFile(outputFile)
  }

  private fun compressFiles(zipOutputStream: ZipOutputStream, files: Array<String>) {
    zipOutputStream.use { out ->
      files.forEach { file ->
        FileInputStream(file).use { fileInput ->
          BufferedInputStream(fileInput).use { origin ->
            val entry = ZipEntry(file.substring(file.lastIndexOf("/")))
            out.putNextEntry(entry)
            origin.copyTo(out, COMPRESS_BUFFER_CHUNK)
          }
        }
      }
    }
  }

  fun uploadFile(fileUri: Uri) {
    val file = File(fileUri.path)

    val requestBody = MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart(MULTIPART_NAME, file.name, RequestBody.create(null, file))
        .build()

    val request = Request.Builder()
        .url(SERVER_UPLOAD_PATH)
        .post(requestBody)
        .build()

    val response = okHttpClient.newCall(request).execute()

    Log.d(LOG_TAG, "onResponse - Status: ${response?.code()} Body: ${response?.body()?.string()}")
  }
}