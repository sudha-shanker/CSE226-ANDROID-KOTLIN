package com.example.cse227_android_kotlin


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat

class ForegroundServicesExample : AppCompatActivity() {
    lateinit var btnStart:Button
    lateinit var btnStop:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground_services_example)
        btnStart=findViewById(R.id.buttonStart)
        btnStop=findViewById(R.id.buttonStop)

        btnStart.setOnClickListener {
            val startIntent = Intent(this, ForegroundService::class.java)
            startIntent.putExtra("inputExtra", "Foreground Service is running...")
            ContextCompat.startForegroundService(this, startIntent)

        }

        btnStop.setOnClickListener {
            val stopIntent = Intent(this, ForegroundService::class.java)
            stopService(stopIntent)
        }
    }


}