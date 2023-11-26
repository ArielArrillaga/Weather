package ar.com.tdm.weather.dao;

import java.util.ArrayList;

import ar.com.tdm.weather.entities.cities.City;
import ar.com.tdm.weather.exceptions.CustomException;

public interface IDaoCities {
	public int bulkInsertCities (ArrayList<City> cities);
	public String getCode (String city) throws CustomException;
}
