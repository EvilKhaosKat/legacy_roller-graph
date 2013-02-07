/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.postprocessing;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import models.MainContainer;
import models.Measurement;
import models.MeasurementPoint;
import models.newVersion.reader.Reader;

/**
 *
 * @author Kat
 */
public class PostProcessorThreeAverage {

    public Measurement doPostProcess(Measurement postprocessedMeasurement) {
        List<Double> listOfSpeeds = postprocessedMeasurement.getListNeedfulSpeedsData();

        System.out.println("--------------------------------------------------");
        System.out.println("ListOfSpeed:" + listOfSpeeds.size());
        System.out.println("--------------------------------------------------");

        List<MeasurementPoint> listOfNearToBeMaxValues = new LinkedList<MeasurementPoint>();
        List<MeasurementPoint> listOfNearToBeMinValues = new LinkedList<MeasurementPoint>();
        Double value = new Double(0);
        for (int i = 0; i < listOfSpeeds.size(); i++) {
            value = listOfSpeeds.get(i);
            //чуть ниже чем максимальная скорость
            if (value >= 0.9 * MainContainer.maxSpeed) {
                listOfNearToBeMaxValues.add(new MeasurementPoint(value, i * 400L, i));
            }
            //чуть выше чем минимальная скорость. и не с самого начала
            if (i > 10 && (value <= 1.5 * MainContainer.minSpeed)) {
                listOfNearToBeMinValues.add(new MeasurementPoint(value, i * 400L, i));
            }
        }

        System.out.println("--------------------------------------------------");
        System.out.println("listOfNearToBeMaxValues:" + listOfNearToBeMaxValues.size());
        System.out.println("listOfNearToBeMinValues:" + listOfNearToBeMinValues.size());
        System.out.println("--------------------------------------------------");



        //костылеобразо, придумать более оптимальный алгоритм
        List<MeasurementPoint> listOfFirstPeakMaxValues = new LinkedList<MeasurementPoint>();
        List<MeasurementPoint> listOfSecondPeakMaxValues = new LinkedList<MeasurementPoint>();
        List<MeasurementPoint> listOfThirdPeakMaxValues = new LinkedList<MeasurementPoint>();
        MeasurementPoint pmax1 = null;
        MeasurementPoint pmax2 = null;
        MeasurementPoint pmax3 = null;

        int x, y = 0;
        for (x = 0; x < listOfNearToBeMaxValues.size() - 1; x++) {
            if ((listOfNearToBeMaxValues.get(x+1).getNumberInMeasurement() - listOfNearToBeMaxValues.get(x).getNumberInMeasurement()) < 3 /*4 секунды разницы. чтоб отловить разные пики 10000/400*/) {
                listOfFirstPeakMaxValues.add(listOfNearToBeMaxValues.get(x));
            } else {
                y = x + 1;
                break;
            }
        }
        for (x = y; x < listOfNearToBeMaxValues.size() - 1; x++) {
            if ((listOfNearToBeMaxValues.get(x+1).getNumberInMeasurement() - listOfNearToBeMaxValues.get(x).getNumberInMeasurement()) < 3 /*4 секунды разницы. чтоб отловить разные пики 10000/400*/) {
                listOfSecondPeakMaxValues.add(listOfNearToBeMaxValues.get(x));
            } else {
                y = x + 1;
                break;
            }
        }
        listOfThirdPeakMaxValues = listOfNearToBeMaxValues.subList(y, listOfNearToBeMaxValues.size() - 1);
        //три "пика" получены

        System.out.println("--------------------------------------------------");
        System.out.println("listOfFirstPeakMaxValues:" + Arrays.deepToString(listOfFirstPeakMaxValues.toArray()));

        System.out.println("listOfSecondPeakMaxValues:" + Arrays.deepToString(listOfSecondPeakMaxValues.toArray()));

        System.out.println("listOfThirdPeakMaxValues:" + Arrays.deepToString(listOfThirdPeakMaxValues.toArray()));
        System.out.println("--------------------------------------------------");


        pmax1 = getMaxFromMeasurementsList(listOfFirstPeakMaxValues);
        pmax2 = getMaxFromMeasurementsList(listOfSecondPeakMaxValues);
        pmax3 = getMaxFromMeasurementsList(listOfThirdPeakMaxValues);

        System.out.println("--------------------------------------------------");
        System.out.println("pmax1:" + pmax1.getValue() +" "+ pmax1.getNumberInMeasurement()+" "+ pmax1.getTimeInMillis());
        System.out.println("pmax2:" + pmax2.getValue() +" "+ pmax2.getNumberInMeasurement()+" "+ pmax2.getTimeInMillis());
        System.out.println("pmax3:" + pmax3.getValue() +" "+ pmax3.getNumberInMeasurement()+" "+ pmax3.getTimeInMillis());
        System.out.println("--------------------------------------------------");

        //костылеобразо, придумать более оптимальный алгоритм
        List<MeasurementPoint> listOfFirstPeakMinValues = new LinkedList<MeasurementPoint>();
        List<MeasurementPoint> listOfSecondPeakMinValues = new LinkedList<MeasurementPoint>();
        List<MeasurementPoint> listOfThirdPeakMinValues = new LinkedList<MeasurementPoint>();
        MeasurementPoint pmin1 = null;
        MeasurementPoint pmin2 = null;
        MeasurementPoint pmin3 = null;

        x = y = 0;
        for (x = 0; x < listOfNearToBeMinValues.size() - 1; x++) {
            if ((listOfNearToBeMinValues.get(x+1).getNumberInMeasurement() - listOfNearToBeMinValues.get(x).getNumberInMeasurement()) < 3 /*4 секунды разницы. чтоб отловить разные пики 10000/400*/) {
                listOfFirstPeakMinValues.add(listOfNearToBeMinValues.get(x));
            } else {
                y = x + 1;
                break;
            }
        }
        for (x = y; x < listOfNearToBeMinValues.size() - 1; x++) {
            if ((listOfNearToBeMinValues.get(x+1).getNumberInMeasurement() - listOfNearToBeMinValues.get(x).getNumberInMeasurement()) < 3 /*4 секунды разницы. чтоб отловить разные пики 10000/400*/) {
                listOfSecondPeakMinValues.add(listOfNearToBeMinValues.get(x));
            } else {
                y = x + 1;
                break;
            }
        }
        listOfThirdPeakMinValues = listOfNearToBeMinValues.subList(y, listOfNearToBeMinValues.size() - 1);
        //три "пика" получены

        pmin1 = getMinFromMeasurementsList(listOfFirstPeakMinValues);
        pmin2 = getMinFromMeasurementsList(listOfSecondPeakMinValues);
        pmin3 = getMinFromMeasurementsList(listOfThirdPeakMinValues);

        System.out.println("--------------------------------------------------");
        System.out.println("pmin1:" + pmin1.getValue() +" "+ pmin1.getNumberInMeasurement()+" "+ pmin1.getTimeInMillis());
        System.out.println("pmin2:" + pmin2.getValue() +" "+ pmin2.getNumberInMeasurement()+" "+ pmin2.getTimeInMillis());
        System.out.println("pmin3:" + pmin3.getValue() +" "+ pmin3.getNumberInMeasurement()+" "+ pmin3.getTimeInMillis());
        System.out.println("--------------------------------------------------");

        //все крайние точки известны. можно вычислять времена, можно выделить три отдедельных графика
        //----------------------------------------------------------------------

        List<Double> listOfFirstGraphic = new LinkedList<Double>();
        List<Double> listOfSecondGraphic = new LinkedList<Double>();
        List<Double> listOfThirdGraphic = new LinkedList<Double>();

        listOfFirstGraphic = listOfSpeeds.subList(0, pmin1.getNumberInMeasurement());
        listOfSecondGraphic = listOfSpeeds.subList(pmin1.getNumberInMeasurement(), pmin2.getNumberInMeasurement());
        listOfThirdGraphic = listOfSpeeds.subList(pmin2.getNumberInMeasurement(), pmin3.getNumberInMeasurement());

        
        System.out.println("--------------------------------------------------");
        System.out.println("listOfFirstGraphic:" + listOfFirstGraphic.size());
        System.out.println("listOfSecondGraphic:" + listOfSecondGraphic.size());
        System.out.println("listOfThirdGraphic:" + listOfThirdGraphic.size());
        System.out.println("--------------------------------------------------");
        
        
        List<Double> listOfAverage = new LinkedList<Double>();

        int maximumListSize = 0;
        maximumListSize = listOfFirstGraphic.size()>listOfSecondGraphic.size() ? listOfFirstGraphic.size() : listOfSecondGraphic.size();
        maximumListSize = maximumListSize >listOfThirdGraphic.size() ? maximumListSize : listOfThirdGraphic.size();
        System.out.println("Максимальная длина списка координат равна "+maximumListSize);
        
        Double speedFirst = new Double("0");
        Double speedSecond = new Double("0");
        Double speedThird = new Double("0");
        Double resultingSpeed = new Double("0");
        Double minSpeed = new Double (MainContainer.minSpeed);
        for (int i = 0; i < maximumListSize; i++) {
            //listOfAverage.add((listOfFirstGraphic.get(i) + listOfSecondGraphic.get(i) + listOfThirdGraphic.get(i)) / 3);
            speedFirst  = i<(listOfFirstGraphic.size()-1)    ? listOfFirstGraphic.get(i)-1  : minSpeed;
            speedSecond = i<(listOfSecondGraphic.size()-1)   ? listOfSecondGraphic.get(i)-1 : minSpeed;
            speedThird  = i<(listOfThirdGraphic.size()-1)    ? listOfThirdGraphic.get(i)-1  : minSpeed;
            resultingSpeed = (speedFirst + speedSecond + speedThird)/3;
            listOfAverage.add(resultingSpeed);
        }
        postprocessedMeasurement.setListNeedfulSpeedsData(listOfAverage);
        //Measurement tempM = new Measurement();
        //tempM.setListSpeedsData(listOfAverage);
        
        //System.out.println("--------------------------------------------------");
        //System.out.println("время разгона из 1 графика " + pmax1.getTimeInMillis());
        //System.out.println("время выбега из 1 графика " + (pmin1.getTimeInMillis() - pmax1.getTimeInMillis()));
       // System.out.println("--------------------------------------------------");
        
        
        Long accelerationTime1 = pmax1.getTimeInMillis();
        Long decelerationTime1 = (pmin1.getTimeInMillis() - pmax1.getTimeInMillis());

        Long accelerationTime2 = pmax2.getTimeInMillis() - pmin1.getTimeInMillis();
        Long decelerationTime2 = pmin2.getTimeInMillis()-pmax2.getTimeInMillis();
        
        Long accelerationTime3 = pmax3.getTimeInMillis()-pmin2.getTimeInMillis();
        Long decelerationTime3 = pmin3.getTimeInMillis()-pmax3.getTimeInMillis();
        
        Long accelerationTimeResult = (accelerationTime1+accelerationTime2+accelerationTime3)/3;
        Long decelerationTimeResult = (decelerationTime1+decelerationTime2+decelerationTime3)/3;
        
        System.out.println("время разгона итоговое:"+accelerationTimeResult);
        System.out.println("время выбега итоговое:"+decelerationTimeResult);
        
        MainContainer.getMainFrame().setAccelerateTimeCaption(accelerationTimeResult);
        MainContainer.getMainFrame().setDecelerateTimeCaption(decelerationTimeResult);
        
        
        return postprocessedMeasurement;
        //return tempM;
    }

    private MeasurementPoint getMaxFromMeasurementsList(List<MeasurementPoint> list) {
        MeasurementPoint maxPoint = list.get(0);
        for (MeasurementPoint p : list) {
            if (p.getValue() > maxPoint.getValue()) {
                maxPoint = p;
            }
        }
        return maxPoint;
    }

    private MeasurementPoint getMinFromMeasurementsList(List<MeasurementPoint> list) {
        MeasurementPoint minPoint = list.get(0);
        for (MeasurementPoint p : list) {
            if (p.getValue() < minPoint.getValue()) {
                minPoint = p;
            }
        }
        return minPoint;
    }
}
