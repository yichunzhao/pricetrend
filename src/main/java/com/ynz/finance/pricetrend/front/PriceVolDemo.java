package com.ynz.finance.pricetrend.front;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

public class PriceVolDemo extends ApplicationFrame {

    public PriceVolDemo(String title) {
        super(title);

        JFreeChart xyLineChart = ChartFactory.createXYLineChart(
                "price/volume charts",
                "trade day",
                "Price ",
                createDataCollection(),
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(xyLineChart);
        chartPanel.setPreferredSize(new Dimension(560, 367));

        setContentPane(chartPanel);
    }

    private XYSeriesCollection createDataCollection() {
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(createPriceDataset());
        dataset.addSeries(createVolumeDataset());
        return dataset;
    }


//    private JFreeChart createCombinedChart() {
//        // create price subplot 1...
//        XYDataset priceDataset = createPriceDataset();
//
//        ChartFactory.createXYLineChart("Price-Time", "Trade Date", "Price", priceDataset);
//
//
//        //create volume subplot 2 ...
//        XYDataset volumeDataset = createVolumeDataset();
//
//        ChartFactory.createXYLineChart("Volume-Time", "Trade Date", "Volume", volumeDataset);
//
//
//        //make a combined plot ...
//
//
//
//    }
//

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private XYSeries createPriceDataset() {
        // create dataset 1...
        //BasicTimeSeries series1 = new BasicTimeSeries("Price", Day.class);
        final XYSeries series1 = new XYSeries("Price");

        series1.add(1, 12353.3);
        series1.add(2, 13984.3);
        series1.add(3, 12999.4);
        series1.add(4, 14274.3);
        series1.add(5, 15943.5);
        series1.add(6, 14845.3);
        series1.add(7, 17232.3);
        series1.add(8, 14232.2);
        series1.add(9, 13102.2);
        series1.add(10, 14230.2);
        series1.add(11, 11435.2);
        series1.add(12, 14525.3);
        series1.add(13, 13984.3);
        series1.add(14, 12999.4);
        series1.add(15, 14274.3);
        series1.add(16, 15943.5);
        series1.add(17, 16234.6);
        series1.add(18, 17232.3);
        series1.add(19, 14232.2);
        series1.add(20, 13102.2);

        return series1;
    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private XYSeries createVolumeDataset() {
        // create dataset 2...
        XYSeries series2 = new XYSeries("Volume");

        series2.add(1, 500);
        series2.add(2, 100);
        series2.add(3, 350);
        series2.add(4, 975);
        series2.add(5, 675);
        series2.add(6, 525);
        series2.add(7, 675);
        series2.add(8, 700);
        series2.add(9, 250);
        series2.add(10, 225);
        series2.add(11, 425);
        series2.add(12, 600);
        series2.add(13, 300);
        series2.add(14, 325);
        series2.add(15, 925);
        series2.add(16, 525);
        series2.add(17, 775);
        series2.add(18, 725);
        series2.add(19, 125);
        series2.add(20, 150);

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series2);

        return series2;
    }

    public static void main(String[] args) {
        PriceVolDemo priceVolDemo = new PriceVolDemo("demo price day plot");
        priceVolDemo.pack();
        priceVolDemo.setVisible(true);
    }

}
