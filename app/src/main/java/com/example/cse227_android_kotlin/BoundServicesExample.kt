package com.example.cse227_android_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BoundServicesExample : AppCompatActivity() {

    lateinit var start: Button
    lateinit var stop: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_services_example)
        // assigning ID of startButton
        // to the object start
        start = findViewById(R.id.startButton)

        // assigning ID of stopButton
        // to the object stop
        stop = findViewById(R.id.stopButton)

        // declaring listeners for the
        // buttons to make them respond
        // correctly according to the process
        start.setOnClickListener{
            // starting the service
            startService(Intent(this, NewService::class.java))
        }
        stop.setOnClickListener{
            // stopping the service
            stopService(Intent(this, NewService::class.java))
        }
    }


}