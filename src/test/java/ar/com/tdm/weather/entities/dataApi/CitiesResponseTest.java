package ar.com.tdm.weather.entities.dataApi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.com.tdm.weather.entities.cities.City;
import ar.com.tdm.weather.exceptions.CustomException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CitiesResponseTest {

    private CitiesResponse citiesResponse;

    @BeforeEach
    void setUp() {
        citiesResponse = new CitiesResponse();
    }

    @Test
    void testParseCityInfo_WithValidData() throws CustomException {
        String jsonString = "[{\"LocalizedName\": \"City1\", \"Key\": \"1\"}, {\"LocalizedName\": \"City2\", \"Key\": \"2\"}]";

        citiesResponse.parseCityInfo(jsonString);

        List<City> cities = citiesResponse.getCities();

        assertEquals(2, cities.size());
        assertEquals("City1", cities.get(0).getName());
        assertEquals("1", cities.get(0).getCode());
        assertEquals("City2", cities.get(1).getName());
        assertEquals("2", cities.get(1).getCode());
    }

    @Test
    void testParseCityInfo_WithInvalidData() {
        String invalidJsonString = "Invalid JSON";

        assertThrows(CustomException.class, () -> citiesResponse.parseCityInfo(invalidJsonString));
    }
}

