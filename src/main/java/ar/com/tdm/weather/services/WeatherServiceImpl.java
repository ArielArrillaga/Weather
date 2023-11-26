package ar.com.tdm.weather.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ar.com.tdm.weather.dao.IDaoCities;
import ar.com.tdm.weather.entities.weather.TemperatureResponse;
import ar.com.tdm.weather.exceptions.CustomException;
import ar.com.tdm.weather.services.apis.IWeatherDataApi;

@Service
public class WeatherServiceImpl implements IWeatherService {

	private final Logger log = LoggerFactory.getLogger(WeatherServiceImpl.class);

	@Autowired
	IWeatherDataApi serviceApi;
	
	@Autowired
	IDaoCities daoCities;
	
	/**
	 * Este metodo cumple la funcion de mediar entre las distintas partes que hacen al servicio.
	 * obtiene el codigo de la ciudad deseada en la db, obtiene los datos desde la Api, y devuelve los datos obtenidas.
	 * @return {@link TemperatureResponse}
	 */
	@Override
	public TemperatureResponse getTodayWeather(String city) throws CustomException {
		log.info("WeatherServiceImpl: getTodayWeather: Inicio, obteniendo datos para la ciudad: "+city);

		String code = daoCities.getCode(city);
		
		if(code.equals("")) {
			throw new CustomException(404, "La ciudad ingresada no pertenece al grupo top 50");
		}

		return null;
	}

}
