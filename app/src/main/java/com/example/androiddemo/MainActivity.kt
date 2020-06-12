package com.example.androiddemo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val demoList = arrayOf<String>("Localization & Intent", "Linear Layout", "Relative Layout", "Frame Layout", "View Demo", "Style Demo", "Theme Demo",
            "Webview", "VideoView", "CardView", "Toolbar", "Fragment", "Grid", "Recycler", "View Pager", "Broadcast", "Map", "REST API Demo",
            "Bound Service", "Intent Service", "SQLite Demo", "Load Image Demo", "Content provider demo", "Singleton & Observer Demo", "MVC Demo",
            "MVP Demo", "MVVM Demo", "Websocket demo")
        var listView = findViewById<ListView>(R.id.demoListView)

        listView.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,demoList)


        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
        val launched = sharedPreferences.getInt("Launched", 0)
        Toast.makeText(this, "Shared Pref launched: " + launched, Toast.LENGTH_LONG).show()
        val editor = sharedPreferences.edit()
        editor.putInt("Launched" ,launched + 1)
        editor.apply()
        editor.commit()

        val intent = intent
        val message = intent.getStringExtra("fcm_message")
        Log.d("Firebase Service", "From : "+message)

        listView.setOnItemClickListener { parent, view, position, id ->
            if (position == 0) {
                val intent = Intent(this, Multilingual::class.java)
                intent.putExtra("selected", demoList[position])
                startActivity(intent)
            }
            else if (position == 1) {
                val intent = Intent(this, LinearLayoutDemo::class.java)
                startActivity(intent)
            }
            else if (position == 2) {
                val intent = Intent(this, RelativeLayoutDemo::class.java)
                startActivity(intent)
            }
            else if (position == 3) {
                val intent = Intent(this, FrameLayoutDemo::class.java)
                startActivity(intent)
            }
            else if (position == 4) {
                val intent = Intent(this, ViewsDemo::class.java)
                startActivity(intent)
            }
            else if (position == 5) {
                val intent = Intent(this, StyleDemo::class.java)
                startActivity(intent)
            }
            else if (position == 6) {
                val intent = Intent(this, ThemeDemo::class.java)
                startActivity(intent)
            }
            else if (position == 7) {
                val intent = Intent(this, WebViewDemo::class.java)
                startActivity(intent)
            }
            else if (position == 8) {
                val intent = Intent(this, VideoViewDemo::class.java)
                startActivity(intent)
            }
            else if (position == 9) {
                val intent = Intent(this, CardViewDemo::class.java)
                startActivity(intent)
            }
            else if (position == 10) {
                val intent = Intent(this, ToolbarDemo::class.java)
                startActivity(intent)
            }
            else if (position == 11) {
                val intent = Intent(this, FragmentDemo::class.java)
                startActivity(intent)
            }
            else if (position == 12) {
                val intent = Intent(this, GridDemo::class.java)
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
            else if (position == 15) {
                val intent = Intent(this, BroadCastDemo::class.java)
                startActivity(intent)
            }
            else if (position == 16) {
                val intent = Intent(this, GoogleMapDemo::class.java)
                startActivity(intent)
            }
            else if (position == 17) {
                val intent = Intent(this, UserListRESTAPIDemo::class.java)
                startActivity(intent)
            }
            else if (position == 18) {
                val intent = Intent(this, BoundServiceDemo::class.java)
                startActivity(intent)
            }
            else if (position == 19) {
                val intent = Intent(this, IntentServiceDemo::class.java)
                startActivity(intent)
            }
            else if (position == 20) {
                val intent = Intent(this, SQLiteDemo::class.java)
                startActivity(intent)
            }
            else if (position == 21) {
                val intent = Intent(this, LoadImageDemo::class.java)
                startActivity(intent)
            }
            else if (position == 22) {
                val intent = Intent(this, ContentProviderDemo::class.java)
                startActivity(intent)
            }
            else if (position == 23) {
                val intent = Intent(this, SingletonDemo::class.java)
                startActivity(intent)
            }
            else if (position == 24) {
                val intent = Intent(this, MVCDemo::class.java)
                startActivity(intent)
            }
            else if (position == 25) {
                val intent = Intent(this, MVPDemo::class.java)
                startActivity(intent)
            }
            else if (position == 26) {
                val intent = Intent(this, MVVMDemo::class.java)
                startActivity(intent)
            }
            else if (position == 27) {
                val intent = Intent(this, WebsocketDemo::class.java)
                startActivity(intent)
            }
        }
    }
}
