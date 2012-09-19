package models.oldVersion;

import models.MainContainer;
import org.jfree.data.xy.XYSeries;

/**
 *
 */
public class Drawer {
    public static void drawGraphic() {
        XYSeries series1 = new XYSeries("first");
        Double temp;
        for (int i=0;i<MainContainer.getListOfCoordinates().size();i++) {
            temp = (double)i*ReadingOrganizer.getFrequency()/1000;
            System.out.println(temp);
            series1.add(temp,MainContainer.getListOfCoordinates().get(i));
        }
        //series1.add(5,10);
        //series1.add(4,11);
        DrawingOrganizer.getDataset().addSeries(series1);


    }
}
