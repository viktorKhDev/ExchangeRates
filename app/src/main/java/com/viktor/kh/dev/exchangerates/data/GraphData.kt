package com.viktor.kh.dev.exchangerates.data

import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries




class GraphData {

    var list = mutableListOf<com.viktor.kh.dev.exchangerates.data.DataPoint>()



  /*  Calendar calendar = Calendar.getInstance();
    Date d1 = calendar.getTime();
    calendar.add(Calendar.DATE, 1);
    Date d2 = calendar.getTime();
    calendar.add(Calendar.DATE, 1);
    Date d3 = calendar.getTime();

    GraphView graph = (GraphView) findViewById(R.id.graph);

// you can directly pass Date objects to DataPoint-Constructor
// this will convert the Date to double via Date#getTime()
    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
        new DataPoint(d1, 1),
        new DataPoint(d2, 5),
        new DataPoint(d3, 3)
    });*/



    var series: LineGraphSeries<DataPoint> = LineGraphSeries(
      /*  arrayOf(
            DataPoint(0., 1),
            DataPoint(1, 5),
            DataPoint(2, 3),
            DataPoint(3, 2),
            DataPoint(4, 6)*/
        )




/*
    GraphView graph = (GraphView) findViewById(R.id.graph);
    graph.addSeries(series);

// set date label formatter
    graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
    graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space

// set manual x bounds to have nice steps
    graph.getViewport().setMinX(d1.getTime());
    graph.getViewport().setMaxX(d3.getTime());
    graph.getViewport().setXAxisBoundsManual(true);

// as we use dates as labels, the human rounding to nice readable numbers
// is not necessary
    graph.getGridLabelRenderer().setHumanRounding(false);*/
}