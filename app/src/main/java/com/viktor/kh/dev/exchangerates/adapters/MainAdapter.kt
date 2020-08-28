package com.viktor.kh.dev.exchangerates.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.viktor.kh.dev.exchangerates.R
import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import com.viktor.kh.dev.exchangerates.data.ExchangeRate
import java.util.ArrayList
import javax.inject.Inject


class MainAdapter @Inject constructor(_context:Context, _list:List<ExchangeRate>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

       val list = _list;
       val context = _context;






    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val item  = view
        val curName = view.findViewById<TextView>(R.id.currency_name)
        val curRate = view.findViewById<TextView>(R.id.rate)
        val curCc = view.findViewById<TextView>(R.id.cc)
        val curExDate = view.findViewById<TextView>(R.id.exchange_date)

        fun bind (currencyPojo: CurrencyPojo,context: Context){
            curName.setText(currencyPojo.txt)
            curRate.setText(currencyPojo.rate.toString())
            curCc.setText(currencyPojo.cc)
            curExDate.setText(currencyPojo.exchangedate)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      var inflater = LayoutInflater.from(context)
        return ViewHolder(inflater.inflate(R.layout.item_layout,parent,false))
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listItem  = list.get(position)
        holder.bind(listItem,context)
    }
}