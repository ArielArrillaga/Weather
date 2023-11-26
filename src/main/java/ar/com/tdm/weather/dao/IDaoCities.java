package ar.com.tdm.weather.dao;

import java.util.ArrayList;

import ar.com.tdm.weather.entities.cities.City;

public interface IDaoCities {
	public int bulkInsertCities (ArrayList<City> cities);
}
