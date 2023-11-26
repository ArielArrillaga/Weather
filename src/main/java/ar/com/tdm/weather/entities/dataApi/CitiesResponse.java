package ar.com.tdm.weather.entities.dataApi;

import java.util.ArrayList;

import ar.com.tdm.weather.entities.cities.City;
import lombok.Data;

@Data
/**
 * Respuesta para la interfaz que devuelve las ciudades obtenidas desde la coneccion a la api de un 3ro
 */
public class CitiesResponse {
	private String mensaje;
	private ArrayList<City> cities;
	
	public CitiesResponse() {
		this.cities = new ArrayList<City>(); //Inicializo en null para evitar problemas en caso de alguna exception.
	}
	
	//sobrescribo el metodo de lombok para devolver una copia del arrayList
	public ArrayList<City> getCities(){
		return new ArrayList<City>(cities);
	}
}
