package ar.com.tdm.weather.services;

import ar.com.tdm.weather.entities.cities.AvailableCities;
import ar.com.tdm.weather.exceptions.CustomException;

public interface ICitiesService {
	public AvailableCities loadCities() throws CustomException;
}
