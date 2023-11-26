package ar.com.tdm.weather.entities.dataApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.tdm.weather.entities.cities.City;
import ar.com.tdm.weather.exceptions.CustomException;
import lombok.Data;

@Data
/**
 * Respuesta para la interfaz que devuelve las ciudades obtenidas desde la coneccion a la api de un 3ro
 */
public class CitiesResponse {
	private String mensaje;
	private ArrayList<City> cities;
	
	private final Logger log = LoggerFactory.getLogger(CitiesResponse.class);

	public CitiesResponse() {
		this.cities = new ArrayList<City>(); //Inicializo en null para evitar problemas en caso de alguna exception.
	}
	
	//sobrescribo el metodo de lombok para devolver una copia del arrayList
	public ArrayList<City> getCities(){
		return new ArrayList<City>(cities);
	}
	
	public void parseCityInfo(String jsonString) throws CustomException {
		try {  
			JSONArray jsonArray = new JSONArray(jsonString);

	        this.cities.clear(); // Limpiar la lista existente antes de agregar nuevas ciudades

	        this.cities.addAll(
	                IntStream.range(0, jsonArray.length())
	                        .mapToObj(jsonArray::getJSONObject)
	                        .map(obj -> new City(obj.getString("LocalizedName"), obj.getString("Key")))
	                        .collect(Collectors.toList())
	        );
	        
		}catch(Exception e) {
			log.error("CitiesResponse: parseCityInfo: Error al parsear los datos: "+e);
			throw new CustomException(500,"No fue posible parsear los datos de la respuesta");
		}
    }
	
	
}
