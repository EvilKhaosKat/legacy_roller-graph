package models.oldVersion;

import org.jfree.data.xy.XYSeries;

import java.util.TimerTask;
import models.MainContainer;

/**
 *
 */
public class DrawingTimerTask extends TimerTask {
    @Override
    public void run() {

        //TODO код непосредственно отрисовки
        if (ReadingOrganizer.getCount()<DrawingOrganizer.getCount()) {
        //XYSeries series1 = new XYSeries("first");
            DrawingOrganizer.getSeries1().add(MainContainer.getListOfCoordinates().get(DrawingOrganizer.getCount()),MainContainer.getListOfCoordinates().get(DrawingOrganizer.getCount()));
        //series1.add(4,11);
        DrawingOrganizer.getDataset().addSeries(DrawingOrganizer.getSeries1());
        }
        System.out.println("Meow");
        System.out.println(ReadingOrganizer.getCount() );
        System.out.println(DrawingOrganizer.getCount());

        DrawingOrganizer.setCount(DrawingOrganizer.getCount() + 1);
        if (!ReadingOrganizer.isReading() && (ReadingOrganizer.getCount()==DrawingOrganizer.getCount())) {
            System.out.println("отрисовка завершена");
            this.cancel();
        }


    }
}
