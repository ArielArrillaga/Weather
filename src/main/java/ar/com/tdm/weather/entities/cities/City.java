package ar.com.tdm.weather.entities.cities;

import lombok.Data;

@Data
/**
 * 
 *Esta clase se utiliza para obtener los datos necesarios para el fucionamiento de la app
 *name es el nombre de la ciudad
 *code es el codigo con el que se consultaran los datos de dicha ciudad
 *
 */
public class City {
	private String name;
	private String code;
	
	public City (String name, String code) {
		this.name=name;
		this.code=code;
	}
}
