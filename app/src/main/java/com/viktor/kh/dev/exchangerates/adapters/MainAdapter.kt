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
        val baseCurrency = view.findViewById<TextView>(R.id.base_currency)
        val currency = view.findViewById<TextView>(R.id.currency)
        val saleRateNB = view.findViewById<TextView>(R.id.sale_rate_nb)
        val purchaseRateNB = view.findViewById<TextView>(R.id.purchase_rate_nb)
        val saleRate = view.findViewById<TextView>(R.id.sale_rate)
        val purchaseRate = view.findViewById<TextView>(R.id.purchase_rate)

        fun bind (exchangeRate: ExchangeRate,context: Context){
            baseCurrency.setText(exchangeRate.baseCurrency)
            currency.setText(exchangeRate.currency)
            saleRateNB.setText(exchangeRate.saleRateNB.toString())
            purchaseRateNB.setText(exchangeRate.purchaseRateNB.toString())
            saleRate.setText(exchangeRate.saleRate.toString())
            purchaseRate.setText(exchangeRate.purchaseRate.toString())


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