package models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kat
 */
public interface Filter {
    public List<MeasurementPoint> getFiltratedData(List<MeasurementPoint> dataForFiltration);
    	//List<MeasurementPoint> filtratedData = new ArrayList(dataForFiltration.size()); //TODO возможно размер списка надо чуть побольше, или наоборот на 1 меньше
      public void getFiltratedDataOneIteration();
}
