package ar.com.tdm.weather.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.tdm.weather.entities.cities.AvailableCities;
import ar.com.tdm.weather.exceptions.CustomException;
import ar.com.tdm.weather.services.apis.IWeatherDataApi;

@Service
public class CitiesServiceImpl implements ICitiesService {

	@Autowired
	IWeatherDataApi serviceApi;
	
	@Override
	public AvailableCities loadCities() throws CustomException {
		//este metodo tiene que llamar a un servicio que se encargue de conectarse a accuweather, este debera tener una interfaz generica pensando en un posible cambio de proveedor
		serviceApi.getTopCities(50);
		return null;
	}

}
