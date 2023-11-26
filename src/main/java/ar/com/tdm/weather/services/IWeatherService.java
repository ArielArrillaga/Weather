package ar.com.tdm.weather.services;

import ar.com.tdm.weather.entities.weather.TemperatureResponse;
import ar.com.tdm.weather.exceptions.CustomException;

public interface IWeatherService {
	public TemperatureResponse getTodayWeather(String city) throws CustomException;

}
