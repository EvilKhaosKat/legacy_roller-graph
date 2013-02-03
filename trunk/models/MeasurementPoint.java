package models;

/**
 *
 * @author Kat
 */
public class MeasurementPoint {
    Double value=0.0;
    Long timeInMillis = 0L;
    //TODO исправить костыль
    int numberInMeasurement = 0;
    
    private MeasurementPoint(){}
    
    public MeasurementPoint(Double v) {
        value=v;
        timeInMillis = System.currentTimeMillis();
    }
    
    public MeasurementPoint(Double v, Long time, int n) {
        value = v;
        timeInMillis = time;
        numberInMeasurement = n;
    }
    
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(Long time) {
        this.timeInMillis = time;
    }

    public int getNumberInMeasurement() {
        return numberInMeasurement;
    }

    public void setNumberInMeasurement(int numberInMeasurement) {
        this.numberInMeasurement = numberInMeasurement;
    }
    
    public String toString() {
        return "["+"value:"+value+" time:"+timeInMillis+" no:"+numberInMeasurement+"]";
    }
    
}
