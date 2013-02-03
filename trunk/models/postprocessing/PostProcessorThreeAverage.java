/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.postprocessing;

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
        List<Double> listOfSpeeds = postprocessedMeasurement.getListSpeedsData();

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
        //костылеобразо, придумать более оптимальный алгоритм
        List<MeasurementPoint> listOfFirstPeakMaxValues = new LinkedList<MeasurementPoint>();
        List<MeasurementPoint> listOfSecondPeakMaxValues = new LinkedList<MeasurementPoint>();
        List<MeasurementPoint> listOfThirdPeakMaxValues = new LinkedList<MeasurementPoint>();
        MeasurementPoint pmax1 = null;
        MeasurementPoint pmax2 = null;
        MeasurementPoint pmax3 = null;

        int x, y = 0;
        for (x = 0; x < listOfNearToBeMaxValues.size() - 1; x++) {
            if ((listOfNearToBeMaxValues.get(x).getNumberInMeasurement() - listOfNearToBeMaxValues.get(x + 1).getNumberInMeasurement()) < 3 /*4 секунды разницы. чтоб отловить разные пики 10000/400*/) {
                listOfFirstPeakMaxValues.add(listOfNearToBeMaxValues.get(x));
            } else {
                y = x + 1;
                break;
            }
        }
        for (x = y; x < listOfNearToBeMaxValues.size() - 1; x++) {
            if ((listOfNearToBeMaxValues.get(x).getNumberInMeasurement() - listOfNearToBeMaxValues.get(x + 1).getNumberInMeasurement()) < 3 /*4 секунды разницы. чтоб отловить разные пики 10000/400*/) {
                listOfSecondPeakMaxValues.add(listOfNearToBeMaxValues.get(x));
            } else {
                y = x + 1;
                break;
            }
        }
        listOfThirdPeakMaxValues = listOfNearToBeMaxValues.subList(y, listOfNearToBeMaxValues.size() - 1);
        //три "пика" получены

        pmax1 = getMaxFromMeasurementsList(listOfFirstPeakMaxValues);
        pmax2 = getMaxFromMeasurementsList(listOfSecondPeakMaxValues);
        pmax3 = getMaxFromMeasurementsList(listOfThirdPeakMaxValues);

        //костылеобразо, придумать более оптимальный алгоритм
        List<MeasurementPoint> listOfFirstPeakMinValues = new LinkedList<MeasurementPoint>();
        List<MeasurementPoint> listOfSecondPeakMinValues = new LinkedList<MeasurementPoint>();
        List<MeasurementPoint> listOfThirdPeakMinValues = new LinkedList<MeasurementPoint>();
        MeasurementPoint pMin1 = null;
        MeasurementPoint pMin2 = null;
        MeasurementPoint pMin3 = null;

        x = y = 0;
        for (x = 0; x < listOfNearToBeMinValues.size() - 1; x++) {
            if ((listOfNearToBeMinValues.get(x).getNumberInMeasurement() - listOfNearToBeMinValues.get(x + 1).getNumberInMeasurement()) < 3 /*4 секунды разницы. чтоб отловить разные пики 10000/400*/) {
                listOfFirstPeakMinValues.add(listOfNearToBeMinValues.get(x));
            } else {
                y = x + 1;
                break;
            }
        }
        for (x = y; x < listOfNearToBeMinValues.size() - 1; x++) {
            if ((listOfNearToBeMinValues.get(x).getNumberInMeasurement() - listOfNearToBeMinValues.get(x + 1).getNumberInMeasurement()) < 3 /*4 секунды разницы. чтоб отловить разные пики 10000/400*/) {
                listOfSecondPeakMinValues.add(listOfNearToBeMinValues.get(x));
            } else {
                y = x + 1;
                break;
            }
        }
        listOfThirdPeakMinValues = listOfNearToBeMinValues.subList(y, listOfNearToBeMinValues.size() - 1);
        //три "пика" получены

        pMin1 = getMinFromMeasurementsList(listOfFirstPeakMinValues);
        pMin2 = getMinFromMeasurementsList(listOfSecondPeakMinValues);
        pMin3 = getMinFromMeasurementsList(listOfThirdPeakMinValues);

        //все крайние точки известны. можно вычислять времена, можно выделить три отдедельных графика
        //----------------------------------------------------------------------
        
        List<Double> listOfFirstGraphic = new LinkedList<Double>();
        List<Double> listOfSecondGraphic = new LinkedList<Double>();
        List<Double> listOfThirdGraphic = new LinkedList<Double>();
        
        listOfFirstGraphic = listOfSpeeds.subList(0, pMin1.getNumberInMeasurement());
        listOfSecondGraphic = listOfSpeeds.subList(pMin1.getNumberInMeasurement(), pMin2.getNumberInMeasurement());
        listOfThirdGraphic = listOfSpeeds.subList(pMin2.getNumberInMeasurement(), pMin3.getNumberInMeasurement());
        
        List<Double> listOfAverage = new LinkedList<Double>();
        for (int i=0;i<listOfSpeeds.size();i++) {
            listOfAverage.add(listOfFirstGraphic.get(i) + listOfSecondGraphic.get(i) + listOfThirdGraphic.get(i)/3);
        }
        postprocessedMeasurement.setListSpeedsData(listOfAverage);
        return postprocessedMeasurement;
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
