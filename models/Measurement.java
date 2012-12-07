package models;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Kat
 */
public class Measurement implements Serializable{
    //TODO проще, гораздо лучше будет сделать хранение двух чисел. непосредственно значения и привязки ко времени. это упростит код отрисовщика(он имеет уже обе координаты). а также логику работы (доизмерение , достроение графика, в другом месте, не с нуля времени. и т.п. извращения)

    private List<Integer> listRawData = Collections.synchronizedList(new LinkedList());
    private List<Double> listSpeedsData = Collections.synchronizedList(new LinkedList());
    private List<Double> listNeedfulSpeedsData = Collections.synchronizedList(new LinkedList());
    private double conversionRawToSpeedCoefficient = 0.0641025641025641; // 1/128=0.0078125;*60*2 = 0.975. и умножим на 40 деленные на 585 0,0683760683760684 =
    
    private double specialPseudoSpeed = 0;

    public void addMeasure(int m) {
        listRawData.add(m);
        calculateSpeed();

    }

    private void calculateSpeed() {
        double speed = 0;
        if (listRawData.size() == 1) {
            speed = 0;
        } else {
            double delta = listRawData.get(listRawData.size() - 1) - listRawData.get(listRawData.size() - 2);
            System.out.println("delta=" + delta);
            speed = delta * conversionRawToSpeedCoefficient;

        }
        System.out.println("speed:" + speed);
        listSpeedsData.add(speed);
        if ( (speed >= MainContainer.minSpeed) && (speed <= MainContainer.maxSpeed)) {
            listNeedfulSpeedsData.add(speed);
            System.out.println("add to needful list");
        } else {
            System.out.println("not enough speed");
        }

    }

    //геттеры и сеттеры
    public List<Integer> getListRawData() {
        return listRawData;
    }

    public List<Double> getListSpeedsData() {
        return listSpeedsData;
    }

    public double getConversionRawToSpeedCoefficient() {
        return conversionRawToSpeedCoefficient;
    }

    public void setConversionRawToSpeedCoefficient(double conversionRawToSpeedCoefficient) {
        this.conversionRawToSpeedCoefficient = conversionRawToSpeedCoefficient;
    }

    public List<Double> getListNeedfulSpeedsData() {
        return listNeedfulSpeedsData;
    }
    
    public double getCurrentSpeed() {
    	//specialPseudoSpeed+=30;
    	if (listSpeedsData.size() == 0) return 0;
    	return listSpeedsData.get(listSpeedsData.size()-1)/*+specialPseudoSpeed*/;
    }
}
