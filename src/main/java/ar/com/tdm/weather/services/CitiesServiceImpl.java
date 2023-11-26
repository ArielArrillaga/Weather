package ar.com.tdm.weather.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.tdm.weather.dao.IDaoCities;
import ar.com.tdm.weather.entities.cities.AvailableCities;
import ar.com.tdm.weather.entities.dataApi.CitiesResponse;
import ar.com.tdm.weather.exceptions.CustomException;
import ar.com.tdm.weather.services.apis.IWeatherDataApi;

@Service
public class CitiesServiceImpl implements ICitiesService {

	private final Logger log = LoggerFactory.getLogger(CitiesServiceImpl.class);

	@Autowired
	IWeatherDataApi serviceApi;
	
	@Autowired
	IDaoCities daoCities;
	
	/**
	 * Este metodo cumple la funcion de mediar entre las distintas partes que hacen al servicio.
	 * Obtiene los datos desde la Api, los carga en la db y devuelve los nombres de las ciudades obtenidas.
	 * @return {@link AvailableCities}
	 */
	@Override
	public AvailableCities loadCities() throws CustomException {
		log.info("CitiesServiceImpl: loadCities: Inicio, se obtendran las ciudades para almacenarlas en la DB");
		AvailableCities response = new AvailableCities();
		
		CitiesResponse cities = serviceApi.getTopCities(50); //para este ejemplo solo dejo que se obtengan 50 ciudades
		log.info("CitiesServiceImpl: loadCities: Ciudades obtenidas desde la api");

		int insertedRows = daoCities.bulkInsertCities(cities.getCities());
		
		if (insertedRows<0) {
			throw new CustomException(500, "No fue posible insertar los datos en la base de datos");
		}
		log.info("CitiesServiceImpl: loadCities: Insercion de datos en la DB realizado correctamente");

		response.mapCities(cities);
		response.setMensaje("Estas son las ciudades disponibles en la base de datos");
		
		log.info("CitiesServiceImpl: loadCities: Mapeo de clases realizado exitosamente");

		return response;
	}

}
