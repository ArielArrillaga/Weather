package ar.com.tdm.weather.services.apis;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ar.com.tdm.weather.entities.dataApi.CitiesResponse;
import ar.com.tdm.weather.entities.weather.TemperatureResponse;
import ar.com.tdm.weather.exceptions.CustomException;
import ar.com.tdm.weather.utils.CallHttp;

@Service
public class AccuWeatherApi implements IWeatherDataApi {

	private final Logger log = LoggerFactory.getLogger(AccuWeatherApi.class);

    @Value("${url.accuWeather.topCities}")
    private String urlTopCities;
    
    @Value("${url.accuWeather.todayWeather}")
    private String urlTodayWeather;
	
	@Value("${accuWeather.key}")
    private String key;
	
	/**
	 * Este metodo se encarga de obtener las ciudades "top", solo se debe indicar la cantidad de ciudades deseadas
	 * @Param citiesNumber (int)
	 * @Return {@link CitiesResponse}
	 */
	@Override
	public CitiesResponse getTopCities(int citiesNumber) throws CustomException {
		CitiesResponse response = new CitiesResponse();
		if (citiesNumber!=50 && citiesNumber!=100 && citiesNumber!=150 ) {
			log.error("AccuWeatherApi: getTopCities: El numero ingresado no es valido, numeros validos: 50, 100, 150.");
			response.setMensaje("Error, solo se aceptan los números 50, 100 y 150");
			return response;
		}
		URL url = null;
		try {
			url = new URL(urlTopCities + citiesNumber + key);
		} catch (MalformedURLException e) {
			log.error("AccuWeatherApi: getTopCities: La url no tiene un formato valido: error: " +e + " url: "+url);
			throw new CustomException(500,"Error interno al formar la url para conectarce con la api accuWeather");
		}
		
		try {
			String resultado = CallHttp.llamadoHttpGet(url);
			if (resultado != null && !resultado.isEmpty()) {
				response.parseCityInfo(resultado);
				log.info("AccuWeatherApi: getTopCities: Ciudades recuperadas exitosamente.");
				response.setMensaje("Ciudades obtenidas Exitosamente");	
				return response;
			}else {
				log.info("AccuWeatherApi: getTopCities: La consulta devolvio un arreglo vacio o nulo.");
				throw new CustomException(204,"Respuesta de la api inesperada.");
			}
		} catch (IOException e) {
			log.error("AccuWeatherApi: getTopCities: Ocurio un error al realizar el llamado: error: " +e + " url: "+url);
			throw new CustomException(500,"Error interno al conectarce con la api accuWeather");
		}
	}

	
	/**
	 * Este metodo se encarga de obtener los datos del clima para una determinada ciudad
	 * @param city (String)
	 * @return {@link TemperatureResponse}
	 */
	@Override
	public TemperatureResponse getTodayWeather(String code) throws CustomException {
		TemperatureResponse response = new TemperatureResponse();
		if(code==null || code.equals("") ) {
			log.error("AccuWeatherApi: getTodayWeather: El codigo ingresado no es valido.");
			response.setMensaje("Error, El codigo ingresado no es valido.");
			return response;
		}
		URL url = null;
		try {
			url = new URL(urlTodayWeather + code + key);
		} catch (MalformedURLException e) {
			log.error("AccuWeatherApi: getTodayWeather: La url no tiene un formato valido: error: " +e + " url: "+url);
			throw new CustomException(500,"Error interno al formar la url para conectarce con la api accuWeather");
		}
		
		try {
			String resultado = CallHttp.llamadoHttpGet(url);
			if (resultado != null && !resultado.isEmpty()) {
				response.parseWeatherInfo(resultado);
				log.info("AccuWeatherApi: getTodayWeather: Datos recuperados exitosamente.");
				response.setMensaje("Datos del clima obtenidos Exitosamente");	
				return response;
			}else {
				log.info("AccuWeatherApi: getTodayWeather: La consulta devolvio un arreglo vacio o nulo.");
				throw new CustomException(204,"Respuesta de la api inesperada.");
			}
		} catch (IOException e) {
			log.error("AccuWeatherApi: getTodayWeather: Ocurio un error al realizar el llamado: error: " +e + " url: "+url);
			throw new CustomException(500,"Error interno al conectarce con la api accuWeather");
		}
	}

}
