package models;

import java.util.Random;

/**
 * @author  KhaosKat
 */
public class generatorOfCoordinates {
    static Random random = new Random();


    public static int getX() {
        return random.nextInt(10);
    }


}
