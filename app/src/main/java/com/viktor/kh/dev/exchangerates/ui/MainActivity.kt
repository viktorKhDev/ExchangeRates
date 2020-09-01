package com.viktor.kh.dev.exchangerates.ui

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Button
import com.viktor.kh.dev.exchangerates.R

class MainActivity  : AppCompatActivity() {

   lateinit var getAllCoursesBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAllCoursesBtn = findViewById(R.id.get_all_courses_btn)
        getAllCoursesBtn.setOnClickListener(View.OnClickListener {
            initMainList()
        })

    }






   fun initMainList(){
       val fragment : Fragment = CoursesFragment()
       supportFragmentManager.beginTransaction().replace(R.id.main_container,fragment).addToBackStack(null).commit()
       getAllCoursesBtn.visibility = View.GONE
       
   }

}