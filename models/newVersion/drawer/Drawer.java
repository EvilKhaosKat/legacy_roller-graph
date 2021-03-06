package models.newVersion.drawer;

import java.util.Timer;
import java.util.TimerTask;
import models.Measurement;
import models.newVersion.reader.Reader;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Kat
 */
public class Drawer {
    private Reader reader;
    private Measurement measurement;
    private XYSeriesCollection dataset;
    
    private int count = 0; //количество отрисованных значений
    private int frequency = 400; //в миллисекундах, и это не frequency
    private boolean stopAfterDrawAllPoints = false;
    
    TimerTask task;
    
    public Drawer(Reader r, XYSeriesCollection dataset) {
        reader=r;
        this.dataset=dataset;
        measurement=reader.getMeasurement();
    }
    
    //TODO поле принудительной остановки не используется, отрисовщик на самом деле не останавливается. исправить
    public Drawer(Measurement m, XYSeriesCollection dataset, boolean b) {
        //reader=r;
        this.dataset=dataset;
        measurement=m;
        
    }
    
    
    private Drawer() {}
    
    public void startDrawing() {
        count=0;
        Timer timer = new Timer();
        task = new DrawerTimerTask(this);
        
        timer.scheduleAtFixedRate(task,0,frequency);

    }
    
    //геттеры и сеттеры

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public XYSeriesCollection getDataset() {
        return dataset;
    }

    public Measurement getMeasurement() {
        return measurement;
    }
    
    
}
