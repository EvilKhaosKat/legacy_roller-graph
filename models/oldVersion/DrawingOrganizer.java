package models.oldVersion;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 */
public class DrawingOrganizer {
    private static int count = 0; //количество отрисованных значений
    private static int frequency = 100;

    private static XYSeriesCollection dataset;

    private static XYSeries series1 = new XYSeries("first");

     public static Timer timer;
    /** Задача таймера, т.е. действия которые он выполнит при срабатывании */
    public static TimerTask task  = new DrawingTimerTask();


    public static void startDrawing() {
        count=0;
        timer = new Timer();
        timer.scheduleAtFixedRate(task,0,frequency);

    }


    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        DrawingOrganizer.count = count;
    }

    public static int getFrequency() {
        return frequency;
    }

    public static void setFrequency(int frequency) {
        DrawingOrganizer.frequency = frequency;
    }

    public static XYSeriesCollection getDataset() {
        return dataset;
    }

    public static void setDataset(XYSeriesCollection dataset) {
        DrawingOrganizer.dataset = dataset;
    }

    public static XYSeries getSeries1() {
        return series1;
    }

    public void setSeries1(XYSeries series1) {
        this.series1 = series1;
    }
}
