package com.viktor.kh.dev.exchangerates.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.viktor.kh.dev.exchangerates.R
import com.viktor.kh.dev.exchangerates.adapters.MainAdapter
import com.viktor.kh.dev.exchangerates.data.ExchangeRate
import com.viktor.kh.dev.exchangerates.di.App
import com.viktor.kh.dev.exchangerates.presenters.MainPresenter
import com.viktor.kh.dev.exchangerates.presenters.MainView
import kotlinx.android.synthetic.main.main_list_fragment.*
import javax.inject.Inject

class CoursesFragment : MainView, androidx.fragment.app.Fragment() {

    @Inject
    lateinit var mainPresenter: MainPresenter
    lateinit var mainAdapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val view :View = inflater.inflate(R.layout.main_list_fragment,container,false)
        App.component.inject(this)
        mainPresenter.init(this)
        val bundle: Bundle? = arguments
        if (bundle != null) {
            mainPresenter.getCourses(bundle.getString("selectedDate",""))
        }
        val getFullListBtn = view.findViewById<Button>(R.id.get_full_list_btn)
        getFullListBtn.setOnClickListener(View.OnClickListener {
            mainPresenter.initFullList()
            get_full_list_btn.visibility = View.GONE
        })
       return view

    }



    override fun initShortList(list: List<ExchangeRate>,date: String) {
        text_date.text = date
        mainAdapter = MainAdapter(requireContext(),list)
        main_list.apply {
            layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = mainAdapter
        }
        mainAdapter.notifyDataSetChanged()
    }


    override fun initFullList(list: List<ExchangeRate>,date: String){
        text_date.text = date
        mainAdapter = MainAdapter(requireContext(),list)
        main_list.apply {
            layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = mainAdapter
        }
        mainAdapter.notifyDataSetChanged()
    }














}


