package models;

/**
 * вместо этого класса будет класс который напрямую связан с протоколом
 * и который будет возвращать очередное integer число - значение со счетчика
 */
public class TempClassIntegerDataGetter {
    public static int getInt() {
        return GeneratorOfCoordinates.getX();
    }
}
