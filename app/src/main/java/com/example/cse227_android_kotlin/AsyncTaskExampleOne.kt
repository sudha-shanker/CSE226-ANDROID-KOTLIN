package com.example.cse227_android_kotlin

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.*

@Suppress("DEPRECATION")
class AsyncTaskExampleOne : AppCompatActivity() {

    lateinit var pb: ProgressBar
    lateinit var lv: ListView
    var ar = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
    lateinit var al: ArrayList<String?>
    lateinit  var ad: ArrayAdapter<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task_example_one)
        pb = findViewById<View>(R.id.pb) as ProgressBar
        lv = findViewById<View>(R.id.lv) as ListView
        al = ArrayList()
        ad = ArrayAdapter<String?>(this, android.R.layout.simple_list_item_1, al)
        lv.adapter=ad
        MyTask().execute()
    }

    //1st parameter: The type of the input variables value you want to set to the background process. This can be an array of objects.
    //
    //2nd parameter – The type of the objects you are going to enter in the onProgressUpdate method.
    //
    //3rd parameter – The type of the result from the operations you have done in the background process.
    internal inner class MyTask :
        AsyncTask<Void?, Int?, String>() {
        var count = 0
        override fun onPreExecute() {
            super.onPreExecute()
            pb.max = 10
            pb.progress = 0
            pb.visibility = View.VISIBLE
            count = 0
        }
        protected   override fun doInBackground(vararg p0: Void?): String? {
            for (i in 1..10) {
                count = count + 1
                publishProgress(i)
                try {
                    Thread.sleep(1000)
                    //   al.add(ar[count - 1])
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            return "Completed"
        }

        protected override fun onProgressUpdate(vararg values: Int?) {
            // super.onProgressUpdate(values);
            pb.progress = values[0]!!
            al.add(ar[count-1]);
            ad.notifyDataSetChanged();
        }

        override fun onPostExecute(s: String) {
            // super.onPostExecute(s);
            Toast.makeText(this@AsyncTaskExampleOne, s, Toast.LENGTH_SHORT).show()
            //  ad.notifyDataSetChanged()
            pb.visibility = View.INVISIBLE
        }
    }
}
//change style="@style/Widget.AppCompat.ProgressBar.Horizontal" in xml
//or @style/Widget.AppCompat.ProgressBar