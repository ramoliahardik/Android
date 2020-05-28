package com.example.androiddemo

import android.annotation.TargetApi
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_content_provider_demo.*

class ContentProviderDemo : AppCompatActivity() {

    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider_demo)

        context = this
        getContact()
    }

    @TargetApi(26)
    fun getContact() {
        val contacts : MutableList<String> = ArrayList()
        val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                contacts.add(name)
            } while (cursor.moveToNext())
            cursor.close()
        }
        actvContent.setAdapter(ArrayAdapter(context, android.R.layout.simple_list_item_1, contacts))
        actvContent.threshold = 1

        actvContent.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(context, "Selected: " + parent.getItemAtPosition(position), Toast.LENGTH_LONG).show()
        }
    }
}
