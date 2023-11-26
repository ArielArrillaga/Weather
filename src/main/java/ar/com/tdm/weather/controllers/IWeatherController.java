package ar.com.tdm.weather.controllers;

import org.springframework.http.ResponseEntity;

import ar.com.tdm.weather.entities.weather.TemperatureResponse;

public interface IWeatherController {
	public ResponseEntity<TemperatureResponse> getTodayWeather(String  city);
}
