package com.example.androiddemo

import android.annotation.TargetApi
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ImageView
import coil.api.load

class LoadImageDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_image_demo)

        val imgLoad = findViewById<ImageView>(R.id.imgLoad)
        imgLoad.load("https://i.ytimg.com/vi/8Zmhd8-Xi5E/maxresdefault_live.jpg")
    }
}
