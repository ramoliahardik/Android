package com.example.androiddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import android.view.View


class FragmentDemo : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    var isFrag1Loaded = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_demo)

        val btnChange = findViewById<Button>(R.id.btnChange)

        btnChange.setOnClickListener {
            if (isFrag1Loaded) {
                showFrag2()
            }
            else {
                showFrag1()
            }
        }


    }

    fun showFrag1() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_holder, Fragment1())
        fragmentTransaction.commit()
    }

    fun showFrag2() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_holder, Fragment1())
        fragmentTransaction.commit()
    }
}
