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

        val demoList = arrayOf<String>("Localization & Intent", "Linear Layout", "Relative Layout", "Frame Layout", "View Demo", "Style Demo", "Theme Demo",
            "Webview", "VideoView", "CardView", "Toolbar", "Fragment", "Grid", "Recycler", "View Pager")
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
            else if (position == 4) {
                var intent = Intent(this, ViewsDemo::class.java)
                startActivity(intent)
            }
            else if (position == 5) {
                var intent = Intent(this, StyleDemo::class.java)
                startActivity(intent)
            }
            else if (position == 6) {
                var intent = Intent(this, ThemeDemo::class.java)
                startActivity(intent)
            }
            else if (position == 7) {
                var intent = Intent(this, WebViewDemo::class.java)
                startActivity(intent)
            }
            else if (position == 8) {
                var intent = Intent(this, VideoViewDemo::class.java)
                startActivity(intent)
            }
            else if (position == 9) {
                var intent = Intent(this, CardViewDemo::class.java)
                startActivity(intent)
            }
            else if (position == 10) {
                var intent = Intent(this, ToolbarDemo::class.java)
                startActivity(intent)
            }
            else if (position == 11) {
                var intent = Intent(this, FragmentDemo::class.java)
                startActivity(intent)
            }
            else if (position == 12) {
                var intent = Intent(this, GridDemo::class.java)
                startActivity(intent)
            }
            else if (position == 13) {
                val intent = Intent(this, RecyclerViewDemo::class.java)
                startActivity(intent)
            }
            else if (position == 14) {
                val intent = Intent(this, ViewPagerDemo::class.java)
                startActivity(intent)
            }
        }
    }
}
