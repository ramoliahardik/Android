package com.example.androiddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import okhttp3.*
import java.util.concurrent.TimeUnit

class WebsocketDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_websocket_demo)
    }

    val client = OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS).build()
    val request = Request.Builder().url("wss://echo.websocket.org").build()
    val wsListener = MyWebsocketListener()
    val webSocket = client.newWebSocket(request, wsListener)
}

class MyWebsocketListener: WebSocketListener() {

    companion object {
        private val NORMAL_CLOSURE_STATUS = 1000
    }

    private fun output(txt: String) {
        Log.v("WSS", txt)
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        webSocket.send("Hello...!!!")
        webSocket.send("What's up ...!!!")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        output("Receiving : " + text!!)
    }

    override fun onClosing(webSocket: WebSocket?, code: Int, reason: String?) {
        webSocket!!.close(NORMAL_CLOSURE_STATUS, null)
        output("Closing : $code / $reason")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        output("Error : " + t.message)
    }
}