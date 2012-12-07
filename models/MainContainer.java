package models;

import connection.ComPortPreferences;
import device.si30.SI30Counter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import views.MainFrame;

/**
 * @author KhaosKat
 */
public class MainContainer {
    //старый вариант
    private static List<Integer> listOfCoordinatesNotSync = new LinkedList();
    private static List<Integer> listOfCoordinates =
            Collections.synchronizedList(listOfCoordinatesNotSync);
    ////////////////////////////////////////////////////////////////////////////
    
    
    
    
    private static List<Measurement> listMeasurementsNotSync = new LinkedList();
    private static List<Measurement> listMeasurements =
            Collections.synchronizedList(listMeasurementsNotSync);
    private static SI30Counter si30Counter = new SI30Counter();

    private static MainFrame mainFrame;
    
    private static String defaultEtalonMeasurementFilename = "etalon_measurement";
    
    //TODO тупая идея, спору нет, но это лучшее что я сейчас придумать смог
    public static boolean isReading=false;
    //TODO конкретные значения коэффициентов надо переопределить
    public static double minSpeed=585; //40 km
    public static double maxSpeed = 1462.5; //100 km
    
    public static double speedFirstThreshold = 100; //10 km
    public static double speedSecondThreshold = 200; //20 km
    
    private static ComPortPreferences comPortPreferences;
    
    public static void setComPortName(String comPortName) {
        if (comPortPreferences == null) {
            comPortPreferences = new ComPortPreferences();
        }
        comPortPreferences.setPortName(comPortName);
        System.out.println("Com port setted to "+comPortName);
    }
    
    //геттеры и сеттеры
    public static List<Measurement> getListMeasurements() {
        return listMeasurements;
    }

    public static SI30Counter getSi30Counter() {
        return si30Counter;
    }

    public static List<Integer> getListOfCoordinates() {
        return listOfCoordinates;
    }
    
    public static MainFrame getMainFrame() {
    	return mainFrame;
    }
    
    public static void setMainFrame (MainFrame mf) {
    	mainFrame=mf;
    }

    public static ComPortPreferences getComPortPreferences() {
        return comPortPreferences;
    }

    public static void setComPortPreferences(ComPortPreferences newComPortPreferences) {
        comPortPreferences = newComPortPreferences;
    }

    public static String getDefaultEtalonMeasurementFilename() {
        return defaultEtalonMeasurementFilename;
    }

    public static void setDefaultEtalonMeasurementFilename(String defaultEtalonMeasurementFilename) {
        MainContainer.defaultEtalonMeasurementFilename = defaultEtalonMeasurementFilename;
    }
    
    
    
}
