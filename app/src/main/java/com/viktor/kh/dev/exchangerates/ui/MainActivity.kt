package com.viktor.kh.dev.exchangerates.ui


import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import com.viktor.kh.dev.exchangerates.R
import com.viktor.kh.dev.exchangerates.utils.DATE_FORMAT
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat

import java.util.*

class MainActivity  : AppCompatActivity() {

    lateinit var getAllCoursesBtn: Button
    lateinit var selectedDate: TextView
    lateinit var calendarView: CalendarView
    lateinit var date: Date


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAllCoursesBtn = findViewById(R.id.get_all_courses_btn)
        selectedDate = findViewById(R.id.text_selected_date)
        calendarView = findViewById(R.id.calendarView)

        getAllCoursesBtn.setOnClickListener(View.OnClickListener {
            initMainList()
        })
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
           selectedDate.text = "${dayOfMonth}.${month}.${year}"

        }

        //initDate()

    }


    override fun onBackPressed() {
        if( getAllCoursesBtn.visibility == View.GONE){
            getAllCoursesBtn.visibility = View.VISIBLE
        }

        super.onBackPressed()
    }

    fun initMainList(){
        if(text_selected_date.text.length>1){
            val fragment : Fragment = CoursesFragment()
            val bundle: Bundle = Bundle()
            bundle.putString("selectedDate", text_selected_date.text.toString())
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.main_container,fragment).addToBackStack(null).commit()
            getAllCoursesBtn.visibility = View.GONE
        }else{
            Toast.makeText(this,"Нужно выбрать дату!", Toast.LENGTH_LONG)
        }

       
   }


    private fun initDate(){
        date = Date()
        text_selected_date.text = "${date.day}-${date.month}-${date.year}"

    }








}