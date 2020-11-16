package com.viktor.kh.dev.exchangerates.ui



import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.viktor.kh.dev.exchangerates.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      get_all_courses_btn.setOnClickListener(View.OnClickListener {
          initMainList()
      })
        calendarView.setOnDateChangedListener { widget, date, selected ->

            val day = add0ToStart(date.day.toString())
            var month = add0ToStart((date.month + 1).toString())
            var year = date.year.toString()
            text_selected_date.text = "${day}.${month}.${year}"
        }

        val calendar = Calendar.getInstance()
        val  calendarMin = GregorianCalendar(
            calendar.get(Calendar.YEAR) - 4,
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )


        calendarView.state()
            .edit()
            .setMaximumDate(calendar)
            .setMinimumDate(calendarMin)
            .commit()

    }

    override fun onBackPressed() {

        if( get_all_courses_btn.visibility == View.GONE){
            get_all_courses_btn.visibility = View.VISIBLE
        }

        super.onBackPressed()
    }

    fun initMainList(){
        if(text_selected_date.text.length>1){
            val fragment : androidx.fragment.app.Fragment = CoursesFragment()
            val bundle: Bundle = Bundle()
            bundle.putString("selectedDate", text_selected_date.text.toString())
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment).addToBackStack(
                null
            ).commit()
            get_all_courses_btn.visibility = View.GONE
        }else{
            Toast.makeText(this, "Нужно выбрать дату!", Toast.LENGTH_LONG)
        }

       
   }

    fun add0ToStart(num: String):String{
        if(num.length==1){
            return "0${num}"
        }else{
            return num
        }
    }






}

