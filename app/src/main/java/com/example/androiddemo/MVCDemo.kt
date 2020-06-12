package com.example.androiddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_mvcdemo.*

data class Student(var rollno: String, var name: String)

class StudentController(var model: Student, var view: MVCDemo) {
    fun getName() : String {
        return model.name
    }
    fun getRollno() : String {
        return model.rollno
    }
    fun setName(name: String) {
        model.name = name
    }
    fun setRollno(rollno: String) {
        model.rollno = rollno
    }
    fun updateView() {
        view.printDetails(model.name, model.rollno)
    }
}

class MVCDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvcdemo)

        var view = MVCDemo()

        var controller = StudentController(getData(), view)

        btnStudent.setOnClickListener {
            controller.setRollno("2")
            controller.setName("XYZ")
            controller.updateView()
        }
    }

    fun getData(): Student {
        return Student("1", "ABC")
    }

    fun printDetails(name: String, rollno: String) {
        Log.d("Student", "Name: " + name + " Rollno: "+rollno)
    }
}
