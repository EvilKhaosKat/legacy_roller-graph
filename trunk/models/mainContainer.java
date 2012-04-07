package models;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author KhaosKat
 */
public class mainContainer {
    private static List<Integer> listOfCoordinatesNotSync = new LinkedList();
    private static List<Integer> listOfCoordinates =
            Collections.synchronizedList(listOfCoordinatesNotSync);


    public static List<Integer> getListOfCoordinates() {
        return listOfCoordinates;
    }
}
