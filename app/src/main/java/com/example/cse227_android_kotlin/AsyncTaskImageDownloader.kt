@file:Suppress("DEPRECATION")

package com.example.cse227_android_kotlin

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class AsyncTaskImageDownloader : AppCompatActivity() {
    lateinit  var ImageUrl: URL
    lateinit var `is`:InputStream
    var bmImg: Bitmap? = null
    lateinit var imageView: ImageView
    lateinit var p: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task_image_downloader)
        val button: Button = findViewById(R.id.asyncTask)
        imageView = findViewById(R.id.image)
        button.setOnClickListener{

            val asyncTask = AsyncTaskExample()
            asyncTask.execute("https://www.cleverfiles.com/howto/wp-content/uploads/2018/03/minion.jpg")

        }
    }

    private inner class AsyncTaskExample :  AsyncTask<String?, String?, Bitmap?>() {
        override fun onPreExecute() {
            super.onPreExecute()
            p = ProgressDialog(this@AsyncTaskImageDownloader)
            p.setMessage("Please wait...It is downloading")
            p.setCancelable(false)
            p.show()
        }

        /*
     getInputStream():  used t read data from a source
     HTTPURLConnection: URLConnection is an abstract class. The two subclasses HttpURLConnection and JarURLConnection
     makes the connetion between the client Java program and URL resource on the internet. With the help of URLConnection
     class, a user can read and write to and from any resource referenced by an URL object.
     Here are the steps for anyone browsing:
        Calculate the maximum possible inSampleSize that still yields an image larger than your target.
        Load the image using BitmapFactory. decodeFile(file, options) , passing inSampleSize as an option.
        Resize to the desired dimensions using Bitmap. createScaledBitmap()
        BitmapFactory.options :Creates Bitmap objects from various sources, including files, streams, and byte-arrays.*/
        override fun doInBackground(vararg p0: String?): Bitmap? {
            try {
                ImageUrl = URL(p0[0])
                val conn: HttpURLConnection = ImageUrl.openConnection() as
                        HttpURLConnection
                conn.doInput = true
                conn.connect()
                `is` = conn.getInputStream()
                val options = BitmapFactory.Options()
                options.inPreferredConfig = Bitmap.Config.RGB_565
                bmImg = BitmapFactory.decodeStream(`is`, null, options)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return bmImg
        }

        override fun onPostExecute(bitmap: Bitmap?) {
            super.onPostExecute(bitmap)
            if (imageView != null) {
                p.hide()
                imageView.setImageBitmap(bitmap)
            } else {
                p.show()
            }
        }
    }
}