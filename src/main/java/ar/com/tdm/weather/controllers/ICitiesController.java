package ar.com.tdm.weather.controllers;

import org.springframework.http.ResponseEntity;

import ar.com.tdm.weather.entities.Response;
import ar.com.tdm.weather.entities.cities.AvailableCities;

public interface ICitiesController {

    public ResponseEntity<AvailableCities> loadCities();
    public ResponseEntity<Response> cleanTable();
}
