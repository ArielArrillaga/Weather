package ar.com.tdm.weather.services.apis;

import ar.com.tdm.weather.entities.dataApi.CitiesResponse;
import ar.com.tdm.weather.entities.weather.TemperatureResponse;
import ar.com.tdm.weather.exceptions.CustomException;

public interface IWeatherDataApi {
	public CitiesResponse getTopCities(int citiesNumber) throws CustomException;
	public TemperatureResponse getTodayWeather(String code) throws CustomException;
}
