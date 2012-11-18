package models;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Kat
 */
public class Singleton {
    
    private List<DataList> listOfDataLists = Collections.synchronizedList(new LinkedList());
    
    
    private Singleton() {
    }
    
    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    private static class SingletonHolder {

        private static final Singleton INSTANCE = new Singleton();
    }
}
