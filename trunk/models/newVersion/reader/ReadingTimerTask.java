package models.newVersion.reader;

import java.util.TimerTask;
import models.MainContainer;


/**
 *
 * @author Kat
 */
public class ReadingTimerTask extends TimerTask {
    
    private Reader reader;

  
    public ReadingTimerTask(Reader r) {
        reader=r;
        
    }


    public void run() {


        if (reader.getCount()>reader.getTime()*1000/reader.getFrequency()) {
            System.out.println("чтение завершено");
            //Drawer.drawGraphic();
            //TODO для варианта сначала прочитать потом отрисовать - добавить сюда вызов отрисовщика
            //ReadingOrganizer.setReading(false);
            this.cancel();

        }
        reader.setCount(reader.getCount() + 1);
        int value = MainContainer.getSi30Counter().readCounterValue();
        reader.getMeasurement().addMeasure(value);
        System.out.println("value:"+value);
        //ReaderOfCoordinates.readCoordinate();




    }
}
