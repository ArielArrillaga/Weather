package ar.com.tdm.weather.controllers;

import org.springframework.http.ResponseEntity;

import ar.com.tdm.weather.entities.cities.AvailableCities;

public interface CitiesController {

    public ResponseEntity<AvailableCities> loadCities();
	


}
