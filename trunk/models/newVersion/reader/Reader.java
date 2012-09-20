package models.newVersion.reader;

import java.util.Timer;
import java.util.TimerTask;
import models.MainContainer;
import models.Measurement;


/**
 *
 * @author Kat
 */
public class Reader {

    private float time = 10; // сколько секунд длится считывание
    private int frequency = 1000; //in milliseconds
    private int count = 0; //количество прочитанных значений
    private Timer timer;
    private TimerTask task;//  = new ReadingTimerTask();
    
    private Measurement measurement;

    public Reader(Measurement m) {
        measurement=m;
        
    }
    
    private Reader() {}
    
    public void startReading() {
        MainContainer.isReading=true;
        //MainContainer.getListOfCoordinates().clear();
        count = 0;
        timer = new Timer();
        task = new ReadingTimerTask(this);
        //task.
        timer.scheduleAtFixedRate(task, 0, frequency);
    }

    public void stopReading() {
        timer.cancel();
    }

    //геттеры и сеттеры
    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Measurement getMeasurement() {
        return measurement;
    }
    
    
}
