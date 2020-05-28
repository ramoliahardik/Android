package com.example.androiddemo

import android.content.Intent
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    val TAG = "Firebase Service"

    override fun onMessageReceived(p0: RemoteMessage) {
        Log.d(TAG, "From : "+p0.from)
        Log.d(TAG, "Message : "+p0.notification!!.body!!)

        val intent = Intent(this@MyFirebaseMessagingService, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("fcm_message", p0.notification!!.body!!)
        startActivity(intent)
    }
}