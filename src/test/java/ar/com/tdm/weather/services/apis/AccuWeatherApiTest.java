package ar.com.tdm.weather.services.apis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.com.tdm.weather.entities.dataApi.CitiesResponse;
import ar.com.tdm.weather.entities.weather.TemperatureResponse;
import ar.com.tdm.weather.exceptions.CustomException;

import static org.junit.jupiter.api.Assertions.*;

public class AccuWeatherApiTest {

    private AccuWeatherApi accuWeatherApi;

    @BeforeEach
    public void setUp() {
        accuWeatherApi = new AccuWeatherApi();
     
    }

    @Test
    public void testGetTopCitiesInvalidInput() throws CustomException {
        // Act
        CitiesResponse actualResponse = accuWeatherApi.getTopCities(200);

        // Assert
        assertNotNull(actualResponse);
        assertEquals("Error, solo se aceptan los números 50, 100 y 150", actualResponse.getMensaje());
    }

    @Test
    public void testGetTodayWeatherInvalidInput() throws CustomException {
        // Act
        TemperatureResponse actualResponse = accuWeatherApi.getTodayWeather("");

        // Assert
        assertNotNull(actualResponse);
        assertEquals("Error, El codigo ingresado no es valido.", actualResponse.getMensaje());

    }
}

