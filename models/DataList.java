package models;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Kat
 */
public class DataList {
    Long date = 0L;
    private List<MeasurementPoint> data = Collections.synchronizedList(new LinkedList());
    private List<Filter> filterList = Collections.synchronizedList(new LinkedList());
    
}
