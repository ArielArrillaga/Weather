package ar.com.tdm.weather.entities.weather;

import lombok.Data;

@Data
public class TemperatureValue {
    private int value;
    private String unit;
    private int unitType;
    
    public TemperatureValue (int value, String unit, int unitType) {
    	this.value=value;
    	this.unit=unit;
    	this.unitType=unitType;
    }
}
