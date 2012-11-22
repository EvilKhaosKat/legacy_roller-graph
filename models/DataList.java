package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Kat
 */
public class DataList {
    Long date = 0L;
    private List<MeasurementPoint> data = Collections.synchronizedList(new ArrayList(100));//начальный размер из рассчета 2 измерения и секунд 30-50
    private List<Filter> filterList = Collections.synchronizedList(new ArrayList(10));
    
    
    public DataList() {
    	date = System.currentTimeMillis();
    }

    
    //геттеры и сеттеры

	/**
	 * @return the date
	 */
	public Long getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(Long date) {
		this.date = date;
	}


	/**
	 * @return the data
	 */
	public List<MeasurementPoint> getData() {
		return data;
	}


	/**
	 * @param data the data to set
	 */
	public void setData(List<MeasurementPoint> data) {
		this.data = data;
	}


	/**
	 * @return the filterList
	 */
	public List<Filter> getFilterList() {
		return filterList;
	}


	/**
	 * @param filterList the filterList to set
	 */
	public void setFilterList(List<Filter> filterList) {
		this.filterList = filterList;
	}
    
    
}
