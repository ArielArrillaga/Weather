package ar.com.tdm.weather.entities.weather;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

/**
 * 
 * @author Ariel
 *@apiNote Respuesta del servicio getTodayWeather, devuelve las temperatura maxima y la minima de una cidad en el dia actual
 */
@Data
public class TemperatureResponse {
	private String mensaje;
    private TemperatureValue minimum;
    private TemperatureValue maximum;
    
    /**
     * Parsea el objeto de respuesta de la api a los datos necesarios para el servicio
     * @param json
     * @throws IOException
     */
    public void parseWeatherInfo(String json) throws IOException {
    	ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode rootNode = mapper.readTree(json);
        
        TemperatureResponse temperatureResponse = new TemperatureResponse();
        temperatureResponse.setMensaje(rootNode.path("Headline").path("Text").asText());
        
        JsonNode temperatureNode = rootNode.path("DailyForecasts").get(0).path("Temperature");
        
        this.minimum = new TemperatureValue(temperatureNode.path("Minimum").path("Value").asInt(),
        		temperatureNode.path("Minimum").path("Unit").asText(),
        		temperatureNode.path("Minimum").path("UnitType").asInt());
        
        this.maximum = new TemperatureValue(temperatureNode.path("Maximum").path("Value").asInt(),
        		temperatureNode.path("Maximum").path("Unit").asText(),
        		temperatureNode.path("Maximum").path("UnitType").asInt());
        
    }
}
