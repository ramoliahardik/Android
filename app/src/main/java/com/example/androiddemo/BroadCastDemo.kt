package com.example.androiddemo

import android.app.IntentService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import java.util.*
import kotlin.concurrent.timerTask

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        Toast.makeText(context, "Broadcast Intent Detected.",
            Toast.LENGTH_LONG).show()
    }
}

class MyResultReceiver() : IntentService("receiver") {

    override fun onHandleIntent(intent: Intent?) {
        var resultReceiver = intent!!.getParcelableExtra<ResultReceiver>("receiver")
        var bundle = Bundle()
        resultReceiver.send(1, bundle)
    }

}


class MyDataResultReceiver(val handler: Handler) : ResultReceiver(handler) {
    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {

    }
}

class BroadCastDemo : AppCompatActivity() {

    var receiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broad_cast_demo)

        val c = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = c.activeNetworkInfo
        if (netInfo != null && netInfo.isConnected) {
            if (netInfo.type == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(applicationContext, "System Broadcast: Connected to mobile", Toast.LENGTH_LONG).show()
            }
            if (netInfo.type == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(applicationContext, "System Broadcast: Connected to wifi", Toast.LENGTH_LONG).show()
            }
        }
        else {
            Toast.makeText(applicationContext, "System Broadcast: Not Connected", Toast.LENGTH_LONG).show()
        }
        configureReceiver()

        Timer().schedule(timerTask {
            broadcastIntent()
        }, 2000)
    }

    fun broadcastIntent()
    {
        val intent = Intent()
        intent.action = "com.example.androiddemo"
        intent.flags = Intent.FLAG_INCLUDE_STOPPED_PACKAGES
        sendBroadcast(intent)
    }

    private fun configureReceiver() {
        val filter = IntentFilter()
        filter.addAction("com.example.androiddemo")
        receiver = MyReceiver()
        registerReceiver(receiver, filter)
    }
}
