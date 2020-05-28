package com.example.androiddemo

import android.app.IntentService
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MyIntentService: IntentService("MyIntentService") {
    override fun onHandleIntent(intent: Intent?) {
        Toast.makeText(applicationContext, "Service Started", Toast.LENGTH_LONG).show()
    }
}

class IntentServiceDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_service_demo)

        val intent = Intent(this, MyIntentService::class.java)
        startService(intent)
    }
}
