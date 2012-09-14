package models;

import device.si30.SI30Counter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author KhaosKat
 */
public class MainContainer {
    private static List<Integer> listOfCoordinatesNotSync = new LinkedList();
    private static List<Integer> listOfCoordinates =
            Collections.synchronizedList(listOfCoordinatesNotSync);

    private static SI30Counter si30Counter = new SI30Counter();
    
    public static List<Integer> getListOfCoordinates() {
        return listOfCoordinates;
    }

    public static SI30Counter getSi30Counter() {
        return si30Counter;
    }
    
    
}
