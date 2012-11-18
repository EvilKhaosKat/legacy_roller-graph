package models;

/**
 *
 * @author Kat
 */
public class MeasurementPoint {
    Double value=0.0;
    Long timeInMillis = 0L;

    private MeasurementPoint(){}
    
    public MeasurementPoint(Double v) {
        value=v;
        timeInMillis = System.currentTimeMillis();
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
    
    
}
