package com.example.androiddemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView

class VideoViewDemo : AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_view_demo)

        var vvDemo = findViewById<VideoView>(R.id.vvDemo)
        vvDemo.setVideoPath("/sdcard/blonde_secretary.3gp")
        vvDemo.start()
    }
}
