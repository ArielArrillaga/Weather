package ar.com.tdm.weather.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends Exception {
	private static final long serialVersionUID = -6271265046612541626L;
	
	private int httpCode;
	
	// Constructor sin mensaje personalizado
    public CustomException() {
        super(); // Llama al constructor de la superclase
    }
    
    public CustomException(int httpCode, String mensaje) {
        super(mensaje); // Llama al constructor de la superclase
    	this.httpCode = httpCode;
    }
}
