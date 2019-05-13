/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.StackPane;

/**
 *
 * @author EbmGastroService
 */
public class TempChart {
StackPane root;
    private Double[] X;
    private Double[] Y;
    LineChart lineChart;
    public TempChart(Double[]X,Double[]Y) {
        this.X=X;
        this.Y=Y;
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        lineChart = new LineChart(xAxis, yAxis);
        lineChart.setData(getChartData());
        lineChart.setTitle("Resthitzeschwankung");
        yAxis.setLabel("B a c k o f e n °C");
        xAxis.setLabel("P a l l i n o o s");
        //lineChart.setId("display");   
        lineChart.applyCss();
        

        root = new StackPane();        
        root.setPrefSize(460, 500);
        root.setId("display");   
        
        root.getChildren().add(lineChart);
    }
    public StackPane getRoot(){       
        return root;
    }
    
        
    private ObservableList<XYChart.Series<String, Double>> getChartData() {        
        ObservableList<XYChart.Series<String, Double>> answer = FXCollections
                .observableArrayList();
        Series<String, Double> aSeries = new Series<>();
        Series<String, Double> cSeries = new Series<>();        
        aSeries.setName("Resthitze");
        cSeries.setName("PallTemp");  
        //cSeries.
        for (int i = 0; i < getX().length; i++) {            
            aSeries.getData().add(new XYChart.Data(Integer.toString(i), getX()[i]));            
            cSeries.getData().add(new XYChart.Data(Integer.toString(i), getY()[i]));           
        }      
        
        answer.addAll(aSeries, cSeries);        
        return answer;
    }

    /**
     * @return the X
     */
    public Double[] getX() {        
        return X;
    }

    /**
     * @param x
     * @param y    
     */
    public void setXY(Double[] x,Double[]y) {
        this.X = x;
        this.Y=y;
        lineChart.setData(getChartData());
    }

    /**
     * @return the Y
     */
    public Double[] getY() {
        return Y;
    }

    /**
     * @param Y the Y to set
     */
    public void setY(Double[] Y) {
        this.Y = Y;
    }
}
