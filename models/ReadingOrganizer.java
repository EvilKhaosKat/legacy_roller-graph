package models;

import java.util.Timer;
import java.util.TimerTask;

/**
 * организует чтение всех необходимых данных. с определенной частотой
 */
public class ReadingOrganizer {
    private static float time = 3; // сколько секунд длится считывание
    private static int frequency = 200; //in milliseconds
    private static int count = 0; //количество прочитанных значений
    private static boolean reading = false;

    public static Timer timer;
    /** Задача таймера, т.е. действия которые он выполнит при срабатывании */
    public static TimerTask task  = new ReadingTimerTask();


    public static void startReading() {
        MainContainer.getListOfCoordinates().clear();
        count=0;
        timer = new Timer();
        timer.scheduleAtFixedRate(task,0,frequency);
        reading = true;
    }

    public static void stopReading() {
        timer.cancel();
        reading = false;
    }



    public static float getTime() {
        return time;
    }

    public static void setTime(float time) {
        ReadingOrganizer.time = time;
    }

    public static int getFrequency() {
        return frequency;
    }

    public static void setFrequency(int frequency) {
        ReadingOrganizer.frequency = frequency;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        ReadingOrganizer.count = count;
    }

    public static boolean isReading() {
        return reading;
    }

    public static void setReading(boolean reading) {
        ReadingOrganizer.reading = reading;
    }
}
