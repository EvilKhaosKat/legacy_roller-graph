package models;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Kat
 */
public class Measurement {
    //TODO проще, гораздо лучше будет сделать хранение двух чисел. непосредственно значения и привязки ко времени. это упростит код отрисовщика(он имеет уже обе координаты). а также логику работы (доизмерение , достроение графика, в другом месте, не с нуля времени. и т.п. извращения)
    private List<Integer> listRawDataNotSync = new LinkedList();
    private List<Integer> listRawData =
            Collections.synchronizedList(listRawDataNotSync);
    private List<Integer> listSpeedsNotSync = new LinkedList();
    private List<Integer> listSpeedsData =
            Collections.synchronizedList(listSpeedsNotSync);
    private double conversionRawToSpeedCoefficient = 1;

    public void addMeasure(int m) {
        listRawData.add(m);
        calculateSpeed();
    }

    private void calculateSpeed() {
        int speed = 0;
        if (listRawData.size() == 1) {
            speed = 0;
        } else {
            speed = (int) ((listRawData.get(listRawData.size()-1) - listRawData.get(listRawData.size() - 2)) * conversionRawToSpeedCoefficient);

        }
        System.out.println("speed:"+speed);
        listSpeedsData.add(speed);

    }

    //геттеры и сеттеры
    public List<Integer> getListRawData() {
        return listRawData;
    }

    public List<Integer> getListSpeedsData() {
        return listSpeedsData;
    }

    public double getConversionRawToSpeedCoefficient() {
        return conversionRawToSpeedCoefficient;
    }

    public void setConversionRawToSpeedCoefficient(double conversionRawToSpeedCoefficient) {
        this.conversionRawToSpeedCoefficient = conversionRawToSpeedCoefficient;
    }
}
