package models;

/**
 * считывает значение со счетчик, записывая в LinkedList
 * работает с классом который предоставляет связь с устройством (т.е. тот самый класс который и даст нам
 * integer значение со счетчика
 */
public class ReaderOfCoordinates {
    public static void readCoordinate() {

        MainContainer.getListOfCoordinates().add(TempClassIntegerDataGetter.getInt());
    }
}
