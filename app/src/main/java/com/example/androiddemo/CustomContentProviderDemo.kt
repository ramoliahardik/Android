package com.example.androiddemo

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri

class MyHelper(context: Context?) : SQLiteOpenHelper(context, "ACDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE ACTABLE(_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, MEANING TEXT)")
        db?.execSQL("INSERT INTO ACTABLE(NAME, MEANING) values ('MCA', 'MCAA')")
        db?.execSQL("INSERT INTO ACTABLE(NAME, MEANING) values ('BCA', 'BCAA')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class CustomContentProviderDemo : ContentProvider() {

    companion object{
        val PROVIDER_NAME = "com.example.androiddemo/CustomContentProviderDemo"
        val URL = "content://$PROVIDER_NAME/ACTABLE"
        val CONTENT_URI: Uri = Uri.parse(URL)

        val COLUMN_ID = "_id"
        val COLUMN_NAME = "NAME"
        val COLUMN_MEANING = "MEANING"
    }

    lateinit var db: SQLiteDatabase

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        db.insert("ACTABLE", null, values)
        context?.contentResolver?.notifyChange(uri, null)
        return uri
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return db.query("ACTABLE", projection, selection, selectionArgs, null, null, sortOrder)
    }

    override fun onCreate(): Boolean {
        var helper = MyHelper(getContext())
        db = helper.writableDatabase
        return if (db==null) false else true
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val count = db.update("ACTABLE", values, selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val count = db.delete("ACTABLE", selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun getType(uri: Uri): String? {
        return "vnd.android.cursor..dir/vnd.example.actable"
    }

}
