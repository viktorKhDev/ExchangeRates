package com.viktor.kh.dev.exchangerates.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.viktor.kh.dev.exchangerates.R
import com.viktor.kh.dev.exchangerates.adapters.MainAdapter
import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import com.viktor.kh.dev.exchangerates.data.ExchangeRate
import com.viktor.kh.dev.exchangerates.di.App
import com.viktor.kh.dev.exchangerates.presenters.MainPresenter
import com.viktor.kh.dev.exchangerates.presenters.MainView
import kotlinx.android.synthetic.main.item_layout.*
import javax.inject.Inject

class CoursesFragment : MainView, Fragment() {

    @Inject
    lateinit var mainPresenter: MainPresenter
    lateinit var recyclerView: RecyclerView
    lateinit var mainAdapter: MainAdapter
    lateinit var dateText: TextView
    lateinit var getFullListBtn: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val view :View = inflater.inflate(R.layout.main_list_fragment,container,false)
        dateText = view.findViewById(R.id.text_date)
        recyclerView = view.findViewById(R.id.main_list)
        App.component.inject(this)
        mainPresenter.init(this)
        val bundle: Bundle? = arguments
        if (bundle != null) {
            mainPresenter.getCourses(bundle.getString("selectedDate",""))
        }
        getFullListBtn = view.findViewById(R.id.get_full_list_btn)
        getFullListBtn.setOnClickListener(View.OnClickListener {
            mainPresenter.initFullList()
            getFullListBtn.visibility = View.GONE
        })
       return view

    }



    override fun initShortList(list: List<ExchangeRate>,date: String) {
        dateText.text = date
        mainAdapter = MainAdapter(requireContext(),list)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }
        mainAdapter.notifyDataSetChanged()
    }


    override fun initFullList(list: List<ExchangeRate>,date: String){
        dateText.text = date
        mainAdapter = MainAdapter(requireContext(),list)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }
        mainAdapter.notifyDataSetChanged()
    }














}


