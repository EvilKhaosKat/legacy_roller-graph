package models.oldVersion;

import models.oldVersion.ReaderOfCoordinates;
import models.oldVersion.ReadingOrganizer;

import java.util.TimerTask;

/**

 */
public class ReadingTimerTask extends TimerTask {
    public void run() {


        if (ReadingOrganizer.getCount()>ReadingOrganizer.getTime()*1000/ReadingOrganizer.getFrequency()) {
            System.out.println("чтение завершено");
            Drawer.drawGraphic();
            //TODO для варианта сначала прочитать потом отрисовать - добавить сюда вызов отрисовщика
            ReadingOrganizer.setReading(false);
            this.cancel();

        }
        ReadingOrganizer.setCount(ReadingOrganizer.getCount() + 1);
        ReaderOfCoordinates.readCoordinate();




    }
}
