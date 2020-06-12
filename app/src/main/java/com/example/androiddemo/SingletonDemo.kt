package com.example.androiddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_singleton_demo.*

class UtilProject {
    var text = "No value"
    companion object {
        val instance = UtilProject()
    }
}

object Singletons {
    val utilProject = UtilProject()
}

class SingletonDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singleton_demo)

        etSingleton.setText(Singletons.utilProject.text)

        btnSingleton.setOnClickListener {
            Singletons.utilProject.text = etSingleton.text.toString()
            etSingleton.setText("Observed: Value saved")
        }
    }
}
