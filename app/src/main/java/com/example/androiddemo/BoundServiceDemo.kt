package com.example.androiddemo

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_bound_service_demo.*

class RandomNumberGeneratorService:Service() {
    private val HANDLER_THREAD_NAME="random_number_generator_thread"
    val binder: IBinder = RandomNumberGeneratorServiceBinder()
    lateinit var handlerThread:HandlerThread
    lateinit var handler: Handler
    var randomNumber: Int = -1
    private val TAG = RandomNumberGeneratorService::class.java.simpleName
    inner class RandomNumberGeneratorServiceBinder : Binder(){
        val service: RandomNumberGeneratorService = this@RandomNumberGeneratorService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        mNM =  getSystemService(NOTIFICATION_SERVICE) as NotificationManager;
        Log.d(TAG,"service created")
        handlerThread = HandlerThread(HANDLER_THREAD_NAME)
        handlerThread.start()
        handler = Handler(handlerThread.looper)
        handler.post {
            startGeneratingRandomNumber()
        }
        showNotification()
    }

    lateinit var mNM: NotificationManager
    private val NOTIFICATION = R.string.local_service_started
    lateinit var notificationBuilder: NotificationCompat.Builder
    private fun showNotification() {
        val text = getText(R.string.local_service_started)

        // The PendingIntent to launch our activity if the user selects this notification
        val contentIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, MainActivity::class.java), 0
        )

        // Set the info for the views that show in the notification panel.
        notificationBuilder = NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)  // the status icon
            .setTicker(text)  // the status text
            .setWhen(System.currentTimeMillis())  // the time stamp
            .setContentTitle(getText(R.string.local_service_started))  // the label of the entry
            .setContentText(text)  // the contents of the entry
            .setContentIntent(contentIntent)  // The intent to send when the entry is clicked

        // Send the notification.
        mNM.notify(NOTIFICATION, notificationBuilder.build())
    }

    override fun onDestroy() {

        generateRandomNumber = false

        handler.removeCallbacksAndMessages(null)
        handler.looper.quit()
        handlerThread.quit()
        mNM.cancel(NOTIFICATION)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }
    var generateRandomNumber = false
    private fun startGeneratingRandomNumber() {
        generateRandomNumber = true
        while (generateRandomNumber){
            Thread.sleep(1000)
            randomNumber = (Math.random()*100).toInt()
        }
    }
}

class BoundServiceDemo : AppCompatActivity() {

    var randomNumberGeneratorService: RandomNumberGeneratorService? = null
    private lateinit var connection: ServiceConnection
    var bounded = false
    val TAG = MainActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service_demo)

        connection = object : ServiceConnection{
            override fun onServiceDisconnected(componentName: ComponentName) {
                Log.d(TAG,"Service disconnected.")
                randomNumberGeneratorService = null
            }

            override fun onServiceConnected(componentName: ComponentName
                                            , service: IBinder) {
                Log.d(TAG, "Service connected.")
                randomNumberGeneratorService = (service as RandomNumberGeneratorService.RandomNumberGeneratorServiceBinder).service
            }

        }
        setupViews()
    }

    private fun setupViews() {
        bind_service.setOnClickListener {
            if(!bounded){
                bindService(Intent(this, RandomNumberGeneratorService::class.java),connection,Context.BIND_AUTO_CREATE)
                bounded = true
            }
        }
        get_random_number.setOnClickListener {
            if(!bounded){
                generated_random_number.text = getString(R.string.service_not_bound)
            }else{
                generated_random_number.text = randomNumberGeneratorService?.randomNumber.toString()
            }
        }
        unbind_service.setOnClickListener {
            unbindSafely()
        }
    }

    private fun unbindSafely() {
        if (bounded) {
            unbindService(connection)
            bounded = false
        }
    }

    override fun onDestroy() {
        unbindSafely()
        super.onDestroy()
    }
}
