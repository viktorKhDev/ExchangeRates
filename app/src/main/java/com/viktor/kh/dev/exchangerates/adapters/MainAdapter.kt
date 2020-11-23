package com.viktor.kh.dev.exchangerates.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.viktor.kh.dev.exchangerates.R
import com.viktor.kh.dev.exchangerates.data.DataCourses
import com.viktor.kh.dev.exchangerates.presenters.MainPresenter

import com.viktor.kh.dev.exchangerates.data.ExchangeRate
import com.viktor.kh.dev.exchangerates.utils.DATE_FORMAT
import java.text.SimpleDateFormat
import javax.inject.Inject


class MainAdapter @Inject constructor(_context:Context, dataFragment: DataCourses, _presenter: MainPresenter) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {






    val mapForGraph: Map<String, LineGraphSeries<DataPoint>>? = dataFragment.mapForGraph
    val list = dataFragment.exchangeRates
    val context = _context
    val presenter =  _presenter
    val lastName = dataFragment.lastNameClicked






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(context)
        return ViewHolder(inflater.inflate(R.layout.item_layout,parent,false))
    }


    override fun getItemCount(): Int {
        return list.size

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listItem  = list.get(position)

        if (mapForGraph != null) {
            if(mapForGraph.contains(listItem.currency)){

                holder.bind(listItem,context,mapForGraph.get(listItem.currency),lastName)

            }else{
                holder.bind(listItem,context,null,null)
            }

        }else{
            holder.bind(listItem,context,null,null)

        }
        holder.itemView.setOnClickListener(View.OnClickListener {

            presenter.getDataForGraph(holder.curName)

        })


    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        val currency = view.findViewById<TextView>(R.id.currency)
        val rateateNB = view.findViewById<TextView>(R.id.rate_nb)
        val saleRate = view.findViewById<TextView>(R.id.sale_rate)
        val purchaseRate = view.findViewById<TextView>(R.id.purchase_rate)
        val graph : GraphView = view.findViewById(R.id.graph_container)
        lateinit var curName: String

        fun bind (exchangeRate: ExchangeRate, context: Context, graphSeries: LineGraphSeries<DataPoint>?,lastName:String?){



            var strId :Int = context.resources.getIdentifier(exchangeRate.currency, "string", context.packageName)
            currency.text = context.getString(strId)
            rateateNB.text = convertToVisualString(exchangeRate.saleRateNB)
            saleRate.text = convertToVisualString(exchangeRate.saleRate)
            purchaseRate.text = convertToVisualString(exchangeRate.purchaseRate)


            curName = exchangeRate.currency.toString()

            if (graphSeries!=null){
                graph.visibility = View.VISIBLE

            if (lastName!=null){
                if (lastName==curName){
                    val animation = AnimationUtils.loadAnimation(context,R.anim.item_anim_to_visual_graph)
                    graph.startAnimation(animation)
                }
            }

                graph.addSeries(graphSeries)
                // set date label formatter
                graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(context,SimpleDateFormat(DATE_FORMAT))
                graph.gridLabelRenderer.numHorizontalLabels = 3 // only 4 because of the space

                // set manual x bounds to have nice steps
                graph.viewport.setMinX(graphSeries.lowestValueX)
                graph.viewport.setMaxX(graphSeries.highestValueX)
                graph.viewport.isXAxisBoundsManual = true

                graph.viewport.isYAxisBoundsManual = true
                graph.viewport.setMinY(graphSeries.lowestValueY)
                graph.viewport.setMaxY(graphSeries.highestValueY)


                graph.gridLabelRenderer.setHumanRounding(false)

            }else{

                graph.visibility = View.GONE
            }


        }

        fun convertToVisualString(d:Double?):String{
            return  if (d!=null) String.format("%.2f",d) else " - "
        }


    }
}