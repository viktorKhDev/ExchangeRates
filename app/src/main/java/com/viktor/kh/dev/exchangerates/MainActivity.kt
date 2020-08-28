package com.viktor.kh.dev.exchangerates

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.viktor.kh.dev.exchangerates.adapters.MainAdapter
import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import com.viktor.kh.dev.exchangerates.di.App
import com.viktor.kh.dev.exchangerates.presenters.MainPresenter
import com.viktor.kh.dev.exchangerates.presenters.MainView
import com.viktor.kh.dev.exchangerates.ui.CoursesFragment
import javax.inject.Inject

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
       
   }

}