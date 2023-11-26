package ar.com.tdm.weather.services.apis;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ar.com.tdm.weather.entities.dataApi.CitiesResponse;
import ar.com.tdm.weather.exceptions.CustomException;
import ar.com.tdm.weather.utils.CallHttp;

@Service
public class AccuWeatherApi implements IWeatherDataApi {

	private final Logger log = LoggerFactory.getLogger(AccuWeatherApi.class);

    @Value("${url.accuWeather.topCities}")
    private String urlTopCities;
	
	@Value("${accuWeather.key}")
    private String key;
	
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
			throw new CustomException(500,"Error interno al conectarce con la api accuWeather");
		}
		
		try {
			String resultado = CallHttp.llamadoHttpGet(url);
			System.out.println("prueba");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
//urltopcities + 50 + key
		return null;
	}

}
