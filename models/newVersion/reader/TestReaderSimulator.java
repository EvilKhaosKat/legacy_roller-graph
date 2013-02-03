package models.newVersion.reader;

import java.util.Random;
import java.util.TimerTask;
import models.MainContainer;

/**
 *
 * @author Kat
 */
public class TestReaderSimulator extends TimerTask {

    private Reader reader;
    private int value = 0;
    private int measurementsCount = 0;
    private int graphicNumber = 1;
    Random random = new Random();
    
    public TestReaderSimulator(Reader r) {

        reader = r;
    }

   public void run() {


        if (/*reader.getCount()>reader.getTime()*1000/reader.getFrequency()*/!MainContainer.isReading) {
            System.out.println("чтение завершено");
            //Drawer.drawGraphic();
            //TODO для варианта сначала прочитать потом отрисовать - добавить сюда вызов отрисовщика
            //ReadingOrganizer.setReading(false);
            this.cancel();

        }
        reader.setCount(reader.getCount() + 1);
        
        MainContainer.getMainFrame().setSpeedCaption(reader.getMeasurement().getCurrentSpeed());
        
        measurementsCount++;
        //1462 = 100км
        if ((measurementsCount > 20 )) {
            value = (int) (value + Math.round(80*(25-measurementsCount*0.4)) + random.nextInt(30));
        } else if ( measurementsCount > 1) {
            value = value + Math.round(80*measurementsCount) + random.nextInt(30) ;
        }
        reader.getMeasurement().addMeasure(value);
        System.out.println("value:"+value);
        //ReaderOfCoordinates.readCoordinate();

        
        if ((value > 65000)&&(measurementsCount>48)) {
            //value = 0;
            measurementsCount = 0;
            graphicNumber++;
        } else if ((value > 32000)&&(measurementsCount>48)) {
            measurementsCount = 0;
            graphicNumber++;
        }
        
        System.out.println("Graphic number "+graphicNumber);

    }
}
