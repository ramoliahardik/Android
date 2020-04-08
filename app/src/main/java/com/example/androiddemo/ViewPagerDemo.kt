package com.example.androiddemo

import android.content.Context
import android.graphics.Color
import android.graphics.ColorSpace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager


enum class Model private constructor(val titleResId: Int, val layoutResId: Int) {
    RED(Color.red(1), R.layout.pageone_viewpager),
    BLUE(Color.blue(1), R.layout.pagetwo_viewpager)
}

class CustomPagerAdapter(private val mContext: Context): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val modelObject = Model.values()[position]
        val inflater = LayoutInflater.from(mContext)
        val layout = inflater.inflate(modelObject.layoutResId, container, false) as ViewGroup
        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object`as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return Model.values().size
    }

}

class ViewPagerDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_demo)

        val viewPager = findViewById<ViewPager>(R.id.viewPager1)
        viewPager.adapter = CustomPagerAdapter(this)

    }
}
