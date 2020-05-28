package com.example.androiddemo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_sqlite_demo.*
import kotlinx.android.synthetic.main.sqlite_row_layout.view.*

public class Name {
    var id: Int = 0
    var userName: String? = null
    constructor(id: Int, userName: String) {
        this.id = id
        this.userName = userName
    }
    constructor(userName: String) {
        this.userName = userName
    }
}

public class MindOrksDBOpenHelper(
    context: Context?,
    factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME,
        factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME
                + " TEXT" + ")")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }
    fun addName(name: Name) {
        val values = ContentValues()
        values.put(COLUMN_NAME, name.userName)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
    fun getAllName(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "sampledemo.db"
        val TABLE_NAME = "name"
        val COLUMN_ID = "_id"
        val COLUMN_NAME = "username"
    }
}

class SQLiteAdapter(val items: ArrayList<Name>, val context: Context): RecyclerView.Adapter<SQLiteAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  {
        return ViewHolder (LayoutInflater.from(context).inflate(R.layout.sqlite_row_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
        holder?.tvSQLiteName?.text = items.get(position).userName
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvSQLiteName = view.tvSQLiteName
    }
}



class SQLiteDemo : AppCompatActivity() {

    val dbHandler = MindOrksDBOpenHelper(this, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_demo)

        btnAddSQLite.setOnClickListener {
            dbHandler.addName(Name(1,tvAddSQLite.text.toString()))
            println("Record added")
            showList()
        }

        showList()
    }

    fun showList() {
        var arrList = ArrayList<Name>()

        val cursor = dbHandler.getAllName()
        cursor!!.moveToFirst()

        while (cursor.moveToNext()) {
            arrList.add(Name(1, cursor.getString(cursor.getColumnIndex(MindOrksDBOpenHelper.COLUMN_NAME))))
        }
        cursor.close()
        println("Record fetched"+ arrList)

        val list = findViewById<RecyclerView>(R.id.rvSQLite)
        list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        list.adapter = SQLiteAdapter(arrList, this)
    }
}
