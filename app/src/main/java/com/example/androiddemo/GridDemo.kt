package com.example.androiddemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView

class LanguageItem {
    var icon: Int? = 0
    var name: String? = null

    constructor(icon: Int?, name: String?) {
        this.icon = icon
        this.name = name
    }
}

class LanguageAdapters(var context: Context, var list: ArrayList<LanguageItem>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View = View.inflate(context, R.layout.card_view_item_grid, null)

        var icon: ImageView = view.findViewById(R.id.imgGrid)
        var name: TextView = view.findViewById(R.id.txtGrid)
        var listItem: LanguageItem = list.get(position)
        icon.setImageResource(listItem.icon!!)
        name.text = listItem.name
        return view
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

}

class GridDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_demo)

        var gridView: GridView = findViewById(R.id.gdView)
        var list: ArrayList<LanguageItem> = ArrayList()
        list = setList()
        var adapter = LanguageAdapters(applicationContext, list!!)
        gridView?.adapter = adapter
    }

    private fun setList(): ArrayList<LanguageItem> {
        var list: ArrayList<LanguageItem> = ArrayList()
        list.add(LanguageItem(R.drawable.swifticon, "C Language"))
        list.add(LanguageItem(R.drawable.swifticon, "C Language"))
        list.add(LanguageItem(R.drawable.swifticon, "C Language"))
        list.add(LanguageItem(R.drawable.swifticon, "C Language"))
        list.add(LanguageItem(R.drawable.swifticon, "C Language"))
        return list
    }
}
