package com.viktor.kh.dev.exchangerates.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.viktor.kh.dev.exchangerates.R
import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import com.viktor.kh.dev.exchangerates.data.ExchangeRate
import java.lang.StringBuilder
import java.util.ArrayList
import javax.inject.Inject


class MainAdapter @Inject constructor(_context:Context, _list:List<ExchangeRate>) : androidx.recyclerview.widget.RecyclerView.Adapter<MainAdapter.ViewHolder>() {

       val list = _list.toMutableList()
       val context = _context;



    class ViewHolder (view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val currency = view.findViewById<TextView>(R.id.currency)
        val saleRateNB = view.findViewById<TextView>(R.id.sale_rate_nb)
        val purchaseRateNB = view.findViewById<TextView>(R.id.purchase_rate_nb)
        val saleRate = view.findViewById<TextView>(R.id.sale_rate)
        val purchaseRate = view.findViewById<TextView>(R.id.purchase_rate)

        fun bind (exchangeRate: ExchangeRate,context: Context){

                var strId :Int = context.getResources().getIdentifier(exchangeRate.currency, "string", context.packageName);
                currency.setText(context.getString(strId))
                saleRateNB.setText(convertToVisualString(exchangeRate.saleRateNB!!))
                purchaseRateNB.setText(convertToVisualString(exchangeRate.purchaseRateNB!!))
                saleRate.setText(convertToVisualString(exchangeRate.saleRate!!))
                purchaseRate.setText(convertToVisualString(exchangeRate.purchaseRate!!))




        }

        fun convertToVisualString(d:Double):String{
            return String.format("%.2f",d)
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