package com.viktor.kh.dev.exchangerates.ui



import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.viktor.kh.dev.exchangerates.R
import com.viktor.kh.dev.exchangerates.adapters.MainAdapter
import com.viktor.kh.dev.exchangerates.data.DataForCourses
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
            val date: String = bundle.getString("selectedDate","")
            mainPresenter.getCourses(date)
        }
        val getFullListBtn = view.findViewById<Button>(R.id.get_full_list_btn)
        getFullListBtn.setOnClickListener(View.OnClickListener {
            mainPresenter.isShortList = false
            mainPresenter.initList()
            get_full_list_btn.visibility = View.GONE
        })



        return view

    }



    override fun initList(dataForFragment: DataForCourses) {
        Log.d("MyLog", " initList list size in fragment = ${dataForFragment.exchangeRates.size}")
        text_date.text = dataForFragment.date
        mainAdapter = MainAdapter(requireContext(),dataForFragment,mainPresenter)
        main_list.apply {
            layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = mainAdapter
        }
        mainAdapter.notifyDataSetChanged()

        if(mainPresenter.isShortList == true){
            get_full_list_btn.visibility = View.VISIBLE
        }else{
            get_full_list_btn.visibility = View.GONE
        }

         progress_bar.visibility = View.GONE
    }



    override fun onStop() {
        super.onStop()
        mainPresenter.isMainView = false
    }


    override fun onResume() {
        super.onResume()
        if(mainPresenter.isMainView==false&&mainAdapter.itemCount==0){
            mainPresenter.isMainView = true
            mainPresenter.initList()
            if (!mainPresenter.isShortList){
                get_full_list_btn.visibility = View.GONE
            }

        }
    }

    override fun error(text: String) {
        Toast.makeText(requireContext(),text,Toast.LENGTH_LONG).show()

    }






}

