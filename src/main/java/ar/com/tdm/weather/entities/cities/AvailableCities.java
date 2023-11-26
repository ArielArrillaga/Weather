package ar.com.tdm.weather.entities.cities;

import java.util.ArrayList;

import lombok.Data;

/**
 * 
 * @author Ariel
 * @apiNote Esta clase devolvera un arreglo con los nombres de las 50 ciudades mas tops, acompañado de un mensaje
 */
@Data
public class AvailableCities {
	private String mensaje;
	private ArrayList<String> cities;
	
	public AvailableCities() {
		this.cities = new ArrayList<String>(); //Inicializo en null para evitar problemas en caso de alguna exception.
	}
	
	//sobrescribo el metodo de lombok para devolver una copia del arrayList
	public ArrayList<String> getCities(){
		return new ArrayList<String>(cities);
	}
}
