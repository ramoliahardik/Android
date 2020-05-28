package com.example.androiddemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun fetchUsers(): Call<ArrayList<User>>
}

data class User(val id : Int, val email: String, val name: String, val username: String)

class UserListAdapter(val context: Context, val list: ArrayList<User>): BaseAdapter() {


    interface define

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = LayoutInflater.from(context).inflate(R.layout.user_list_row_layout, parent,false)

        val fname = view.findViewById<TextView>(R.id.tvFirstName)
        val lname = view.findViewById<TextView>(R.id.tvLastName)
        val email = view.findViewById<TextView>(R.id.tvEmail)
        val btnAdd = view.findViewById<Button>(R.id.btnDelete)

        fname.text = list[position].id.toString()
        lname.text = list[position].username
        email.text = list[position].email
        btnAdd.setOnClickListener {
            list.removeAt(position)
            notifyDataSetChanged()
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return  list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

}

class UserListRESTAPIDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list_restapidemo)

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val api = retrofit.create(ApiService::class.java)
        println("API start")
        api.fetchUsers().enqueue(object: Callback<ArrayList<User>> {
            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                println("API Failed")
            }

            override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                println("API success ${response.body()}")
                showData(response.body()!!)
            }
        })
    }

    fun showData(users: ArrayList<User>) {
        val list = findViewById<ListView>(R.id.listUsers)
        list.adapter = UserListAdapter(this, users)
    }
}
