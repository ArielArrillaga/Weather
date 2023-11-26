package ar.com.tdm.weather.entities;

import lombok.Data;

/**
 * 
 * @author Ariel
 *@apiNote esta clase sirve para brindar un mensaje de respuesta ante una solicitud http
 */
@Data
public class Response {
	private String mensaje;
}
