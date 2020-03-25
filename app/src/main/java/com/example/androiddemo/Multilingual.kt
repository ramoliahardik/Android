package com.example.androiddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.Intent
import android.net.Uri

class Multilingual : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multilingual2)

        var tvMultilingual = findViewById<TextView>(R.id.tvMultilingual)
        tvMultilingual.text

        var tvSelected = findViewById<TextView>(R.id.tvSelected)
        tvSelected.text = intent.getStringExtra("selected")

        var btnImplicit = findViewById<Button>(R.id.btnImplicit)
        btnImplicit.setOnClickListener {
            intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://www.google.com/"))
            startActivity(intent)
        }
    }
}
