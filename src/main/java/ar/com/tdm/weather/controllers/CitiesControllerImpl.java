package ar.com.tdm.weather.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.tdm.weather.entities.Response;
import ar.com.tdm.weather.entities.cities.AvailableCities;
import ar.com.tdm.weather.exceptions.CustomException;
import ar.com.tdm.weather.services.ICitiesService;


@RestController
@RequestMapping(path = "/city")
@CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET,
		RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS, RequestMethod.PATCH,
		RequestMethod.HEAD })
public class CitiesControllerImpl implements ICitiesController {
	
	private final Logger log = LoggerFactory.getLogger(CitiesControllerImpl.class);

	@Autowired
	private ICitiesService service;


	/**
	 * este metodo se encarga de realizar el llamado a servicio y retornar los datos cargados en la db o un mensaje de error coon su respectivo codigo http
	 * @return{@link AvailableCities}
	 */
	@Override
	@GetMapping("/loadCities")
	public ResponseEntity<AvailableCities> loadCities() {
		log.info("CitiesControllerImpl: loadCities: " );
		AvailableCities response = new AvailableCities();
		try {
			response = service.loadCities();
			log.info("CitiesControllerImpl: loadCities: " +"response: " + response);
			return new ResponseEntity<AvailableCities>(response, HttpStatus.OK); //200's
		} 
		catch (CustomException e) {
			log.error("CitiesControllerImpl: loadCities: Ocurrio un error." + e);
			response.setMensaje(e.getMessage());
			return new ResponseEntity<AvailableCities>(response, HttpStatus.valueOf(e.getHttpCode())); //400's
		}
		catch (Exception e) {
			log.error("CitiesControllerImpl: loadCities: Error inesperado en el servicio: " + e);
			response.setMensaje("Error inesperado en el servicio");
			return new ResponseEntity<AvailableCities>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Este metodo llamara al servicio que se encarga de vaciar la tabla cities para una posterior recarga
	 *@return {@link Ressponse}
	 */
	@Override
	@DeleteMapping("/cleanTable")
	public ResponseEntity<Response> cleanTable() {
		log.info("CitiesControllerImpl: cleanTable: " );
		Response response = new Response();
		try {
			response = service.cleanTable();
			log.info("CitiesControllerImpl: cleanTable: " +"response: " + response);
			return new ResponseEntity<Response>(response, HttpStatus.OK); //200's
		} 
		catch (CustomException e) {
			log.error("CitiesControllerImpl: cleanTable: Ocurrio un error." + e);
			response.setMensaje(e.getMessage());
			return new ResponseEntity<Response>(response, HttpStatus.valueOf(e.getHttpCode())); //400's
		}
		catch (Exception e) {
			log.error("CitiesControllerImpl: cleanTable: Error inesperado en el servicio: " + e);
			response.setMensaje("Error inesperado en el servicio");
			return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	
}