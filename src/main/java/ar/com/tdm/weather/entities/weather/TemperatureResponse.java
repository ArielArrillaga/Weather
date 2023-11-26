package ar.com.tdm.weather.entities.weather;

import lombok.Data;

@Data
public class TemperatureResponse {
	private String mensaje;
    private TemperatureValue minimum;
    private TemperatureValue maximum;
}
