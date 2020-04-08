package com.example.androiddemo

import android.graphics.RadialGradient
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class ViewsDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_views_demo)

        val tvViewDemo = findViewById<TextView>(R.id.tvViewDemo)

        var typedef = Typeface.createFromAsset(assets, "baamini.ttf")
        tvViewDemo.typeface = typedef

        val btnViewDemo = findViewById<Button>(R.id.btnViewDemo)

        val actvViewDemo = findViewById<AutoCompleteTextView>(R.id.actvViewDemo)

        val cbViewDemo = findViewById<CheckBox>(R.id.cbViewDemo)

        val rb1ViewDemo = findViewById<RadioButton>(R.id.rb1ViewDemo)

        val rb2ViewDemo = findViewById<RadioButton>(R.id.rb2ViewDemo)

        val ivViewDemo = findViewById<ImageView>(R.id.ivViewDemo)

        val pbViewDemo = findViewById<ProgressBar>(R.id.pbViewDemo)

        val seekBar = findViewById<SeekBar>(R.id.seekBar)

        val sbViewDemo = findViewById<SeekBar>(R.id.sbViewDemo)
    }
}
