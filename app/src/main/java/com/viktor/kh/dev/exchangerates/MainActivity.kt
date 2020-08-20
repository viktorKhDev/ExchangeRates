package com.viktor.kh.dev.exchangerates

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import com.viktor.kh.dev.exchangerates.adapters.MainAdapter
import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import com.viktor.kh.dev.exchangerates.di.App
import com.viktor.kh.dev.exchangerates.presenters.MainPresenter
import com.viktor.kh.dev.exchangerates.presenters.MainView
import javax.inject.Inject

class MainActivity  :  MainView, AppCompatActivity() {

    @Inject
    lateinit var mainPresenter:MainPresenter
    lateinit var recyclerView: RecyclerView
    lateinit var mainAdapter: MainAdapter
    lateinit var dateText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.component.inject(this)
        mainPresenter.init(this)
        mainPresenter.getCourses()

    }


    fun init(list: ArrayList<CurrencyPojo>){
        dateText = findViewById(R.id.text_date)
        dateText.text = list[0].exchangedate
        initList(list)

    }


    fun initList(list: ArrayList<CurrencyPojo>){
        recyclerView = findViewById(R.id.main_list)
        mainAdapter = MainAdapter(_context = this,_list = list)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }
        mainAdapter.notifyDataSetChanged()
    }





    override fun setCourses(list: ArrayList<CurrencyPojo>) {
         init(list)
    }





}