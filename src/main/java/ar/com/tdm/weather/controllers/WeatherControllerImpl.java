package ar.com.tdm.weather.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.tdm.weather.entities.cities.AvailableCities;
import ar.com.tdm.weather.entities.weather.TemperatureResponse;
import ar.com.tdm.weather.exceptions.CustomException;
import ar.com.tdm.weather.services.IWeatherService;
@RestController
@RequestMapping(path = "/weather")
@CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET,
		RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS, RequestMethod.PATCH,
		RequestMethod.HEAD })
public class WeatherControllerImpl implements IWeatherController {

	private final Logger log = LoggerFactory.getLogger(WeatherControllerImpl.class);
	
	@Autowired
	IWeatherService service;
	
	/**
	 * Este servicio devolvera la temperatura maxima y minima para una determinada ciudad en el dia actual
	 * @param city (string)
	 * @return {@link TemperatureResponse}
	 */
	@Override
	@GetMapping("/getTodayWeather")
	public ResponseEntity<TemperatureResponse> getTodayWeather(String city) {
		log.info("WeatherControllerImpl: getTodayWeather: " );
		TemperatureResponse response = new TemperatureResponse();
		try {
			response = service.getTodayWeather(city);
			log.info("WeatherControllerImpl: getTodayWeather: " +"response: " + response);
			return new ResponseEntity<TemperatureResponse>(response, HttpStatus.OK); //200's
		} 
		catch (CustomException e) {
			log.error("WeatherControllerImpl: getTodayWeather: Ocurrio un error." + e);
			response.setMensaje(e.getMessage());
			return new ResponseEntity<TemperatureResponse>(response, HttpStatus.valueOf(e.getHttpCode())); //400's
		}
		catch (Exception e) {
			log.error("WeatherControllerImpl: getTodayWeather: Error inesperado en el servicio: " + e);
			response.setMensaje("Error inesperado en el servicio");
			return new ResponseEntity<TemperatureResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
