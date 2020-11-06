package com.viktor.kh.dev.exchangerates.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jjoe64.graphview.GraphView
import com.viktor.kh.dev.exchangerates.R
import com.viktor.kh.dev.exchangerates.data.ExchangeRate
import com.viktor.kh.dev.exchangerates.presenters.MainPresenter
import com.viktor.kh.dev.exchangerates.data.DataForAdapter
import javax.inject.Inject


class MainAdapter @Inject constructor(_context:Context, _list:List<DataForAdapter>, _presenter: MainPresenter) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

       val list = _list.toMutableList()
       val context = _context;
       val presenter =  _presenter







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

        holder.itemView.setOnClickListener(View.OnClickListener {

                presenter.getDataForGraph(holder.curName,holder.adapterPosition)


        })

    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        var graphVisibility = false


        val currency = view.findViewById<TextView>(R.id.currency)
        val saleRateNB = view.findViewById<TextView>(R.id.sale_rate_nb)
        val purchaseRateNB = view.findViewById<TextView>(R.id.purchase_rate_nb)
        val saleRate = view.findViewById<TextView>(R.id.sale_rate)
        val purchaseRate = view.findViewById<TextView>(R.id.purchase_rate)
        val graph : GraphView = view.findViewById(R.id.graph_container)



        lateinit var curName: String





        fun bind (dataForAdapter: DataForAdapter,context: Context){

             val exchangeRate = dataForAdapter.exchangeRate


            var strId :Int = context.getResources().getIdentifier(exchangeRate.currency, "string", context.packageName);
            currency.setText(context.getString(strId))
            saleRateNB.setText(convertToVisualString(exchangeRate.saleRateNB))
            saleRate.setText(convertToVisualString(exchangeRate.saleRate))
            purchaseRateNB.setText(convertToVisualString(exchangeRate.purchaseRateNB))
            purchaseRate.setText(convertToVisualString(exchangeRate.purchaseRate))
            if (dataForAdapter.initGraph!=null){
                graph.visibility = View.VISIBLE
            }else{
                graph.visibility = View.GONE
            }


            curName = exchangeRate.currency.toString()



        }

        fun convertToVisualString(d:Double?):String{
            return  if (d!=null) String.format("%.2f",d) else " - "
        }


    }
}