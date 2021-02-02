package com.viktor.kh.dev.exchangerates.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.viktor.kh.dev.exchangerates.R
import com.viktor.kh.dev.exchangerates.data.DataCourses
import com.viktor.kh.dev.exchangerates.presenters.MainPresenter
import com.viktor.kh.dev.exchangerates.data.ExchangeRate
import com.viktor.kh.dev.exchangerates.data.GraphData
import com.viktor.kh.dev.exchangerates.graph.Graph
import com.viktor.kh.dev.exchangerates.utils.customs.CustomGraph
import javax.inject.Inject


class MainAdapter @Inject constructor(_context:Context, dataFragment: DataCourses, _presenter: MainPresenter) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {







    private val list = dataFragment.exchangeRates
    val context = _context
    private val presenter =  _presenter
    private val graph = Graph(_presenter)
    private val lastName = dataFragment.lastNameClicked






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(context)
        return ViewHolder(inflater.inflate(R.layout.item_layout,parent,false))
    }


    override fun getItemCount(): Int {
        return list.size

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listItem  = list.get(position)

        if (graph != null) {
            /*if(graph.contains(listItem.currency)){

               holder.bind(listItem,context,graph.getGraph(listItem.currency),lastName)

            }else{
                holder.bind(listItem,context,null,null)
            }
*/
        }else{
            holder.bind(listItem,context,null,null)

        }
        holder.itemView.setOnClickListener(View.OnClickListener {

            presenter.getDataForGraph(holder.curName)
            showFirstGraph()

        })


    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        val currency = view.findViewById<TextView>(R.id.currency)
        val rateateNB = view.findViewById<TextView>(R.id.rate_nb)
        val saleRate = view.findViewById<TextView>(R.id.sale_rate)
        val purchaseRate = view.findViewById<TextView>(R.id.purchase_rate)
        val graph : CustomGraph = view.findViewById(R.id.graph_container)
        lateinit var curName: String

        fun bind (exchangeRate: ExchangeRate, context: Context, graphSeries: List<GraphData>?,lastName:String?){



            var strId :Int = context.resources.getIdentifier(exchangeRate.currency, "string", context.packageName)
            currency.text = context.getString(strId)
            rateateNB.text = convertToVisualString(exchangeRate.saleRateNB)
            saleRate.text = convertToVisualString(exchangeRate.saleRate)
            purchaseRate.text = convertToVisualString(exchangeRate.purchaseRate)


            curName = exchangeRate.currency.toString()

            if (graphSeries!=null){
               // graph.visibility = View.VISIBLE

            if (lastName!=null){
                if (lastName==curName){
                    val animation = AnimationUtils.loadAnimation(context,R.anim.item_anim_to_visual_graph)
                    graph.startAnimation(animation)
                }
            }


            }else{

               // graph.visibility = View.GONE
            }


        }

        fun convertToVisualString(d:Double?):String{
            return  if (d!=null) String.format("%.2f",d) else " - "
        }






    }

    private fun showFirstGraph(){
        if (!presenter.isFirstGraph()){
            presenter.showMessage(context.getString(R.string.only_from_db))
            presenter.setFirstGaph()
        }
    }

}