package com.example.androiddemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val demoList = arrayOf<String>("Localization & Intent", "Linear Layout", "Relative Layout", "Frame Layout")
        var listView = findViewById<ListView>(R.id.demoListView)

        listView.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,demoList)

        listView.setOnItemClickListener { parent, view, position, id ->
            if (position == 0) {
                var intent = Intent(this, Multilingual::class.java)
                intent.putExtra("selected", demoList[position])
                startActivity(intent)
            }
            else if (position == 1) {
                var intent = Intent(this, LinearLayoutDemo::class.java)
                startActivity(intent)
            }
            else if (position == 2) {
                var intent = Intent(this, RelativeLayoutDemo::class.java)
                startActivity(intent)
            }
            else if (position == 3) {
                var intent = Intent(this, FrameLayoutDemo::class.java)
                startActivity(intent)
            }
        }
    }
}
