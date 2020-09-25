package com.viktor.kh.dev.exchangerates.ui



import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.viktor.kh.dev.exchangerates.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity  : AppCompatActivity() {

    lateinit var getAllCoursesBtn: Button
    lateinit var selectedDate: TextView
    lateinit var calendarView: MaterialCalendarView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAllCoursesBtn = findViewById(R.id.get_all_courses_btn)
        selectedDate = findViewById(R.id.text_selected_date)
        calendarView = findViewById(R.id.calendarView)

        getAllCoursesBtn.setOnClickListener(View.OnClickListener {
            initMainList()
        })
        calendarView.setOnDateChangedListener { widget, date, selected ->
            selectedDate.text = "${date.day}.${date.month+1}.${date.year}"
        }


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











}