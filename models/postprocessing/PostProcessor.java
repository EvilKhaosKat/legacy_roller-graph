package models.postprocessing;

import java.util.ArrayList;
import java.util.List;
import models.MainContainer;
import models.MeasurementPoint;

/**
 *
 * @author Kat
 */
public class PostProcessor {
    public static List<Double> getAccelerationAndDecelerationTime(List<Double> listOfSpeeds) {
        ArrayList<Double> answer = new ArrayList<Double>(2);
        
        MeasurementPoint maximum = new MeasurementPoint(new Double("0"), 0L, 0);
        
        for (int i=0;i<listOfSpeeds.size();i++) {
            if (maximum.getValue()<listOfSpeeds.get(i)) {
                maximum.setValue(listOfSpeeds.get(i));
                maximum.setNumberInMeasurement(i);
            }
        }
        
        Double accelerationTime = new Double(MainContainer.frequency*maximum.getNumberInMeasurement());
        answer.add(accelerationTime);
        
        Double decelerationTime = new Double(MainContainer.frequency*(listOfSpeeds.size()-1)-accelerationTime);
        answer.add(decelerationTime);
        
        return answer;
    }
    
}
