package models;

import org.extdev.connection.ComPortPreferences;
import org.extdev.device.si30.SI30Counter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import views.MainFrame;

/**
 * @author KhaosKat
 */
public class MainContainer {
    private static List<Measurement> listMeasurementsNotSync = new LinkedList();
    private static List<Measurement> listMeasurements =
            Collections.synchronizedList(listMeasurementsNotSync);
    private static SI30Counter si30Counter = new SI30Counter();

    private static MainFrame mainFrame;
    
    private static String defaultEtalonMeasurementFilename = "etalon_measurement";
    
    //TODO тупая идея, спору нет, но это лучшее что я сейчас придумать смог
    public static boolean isReading=false;
    //TODO конкретные значения коэффициентов надо переопределить
    public static double minSpeed= 40;//585; //40 km
    public static double maxSpeed = 100;//1462.5; //100 km
    
    public static double speedFirstThreshold = 10;//146.25; //10 km
    public static double speedSecondThreshold = 20;//292.5; //20 km
    public static double speedThirdThreshold = 30;//438.75; //20 km
    
    private static ComPortPreferences comPortPreferences;
   
    public final static int WORKMODE_REALTIME_USUAL = 1;
    public final static int WORKMODE_POSTPROCESSING_THREE_AVERAGE = 2;
    public final static int WORKMODE_POSTPROCESSING_THREE_SIMPLIFIED = 3;
    
    private static int workMode = WORKMODE_REALTIME_USUAL;
   
    public static String WORKMODE_TITLE_REALTIME_USUAL = "Режим реального времени.";
    public static String WORKMODE_TITLE_POSTPROCESSING_THREE_AVERAGE = "Режим трёх измерений. График среднего.";
    public static String WORKMODE_TITLE_POSTPROCESSING_THREE_SIMPLIFIED = "Режим трёх измерений. График упрощенный.";
    
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

    public static int getWorkMode() {
        return workMode;
    }

    public static void setWorkMode(int workMode) {
        MainContainer.workMode = workMode;
    }
    
}
