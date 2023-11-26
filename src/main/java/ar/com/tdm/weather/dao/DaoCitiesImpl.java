package ar.com.tdm.weather.dao;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ar.com.tdm.weather.entities.cities.City;

@Repository
public class DaoCitiesImpl implements IDaoCities {
	private final Logger log = LoggerFactory.getLogger(DaoCitiesImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate; 
	
	@Override	
	/**
	 * Este metodo se encaga de conectarse a la DB y realizar una onsersion masiva.
	 * @Param 
	 * @Return int, el cual representa los rows insertados.
	 */
	public int bulkInsertCities(ArrayList<City> cities) {
        String sql = "INSERT INTO cities (name, code) VALUES (?, ?)";
        int[][] affectedRows =  null;
        try {
        	affectedRows = jdbcTemplate.batchUpdate(sql, cities, cities.size(), (ps, city) -> {
                ps.setString(1, city.getName());
                ps.setString(2, city.getCode());
            });
        }catch(Exception e) {
        	log.error("DaoCitiesImpl: bulkInsertCities: Algo salio mal al realizar la insercion de datos: "+e);
        	return -1;
        }

        int totalAffectedRows = 0;
        for (int[] rows : affectedRows) {
            for (int row : rows) {
                totalAffectedRows += row;
            }
        }
    	log.info("DaoCitiesImpl: bulkInsertCities: Insercion exitosa: "+totalAffectedRows+" lineas agregadas a la base de datos.");

        return totalAffectedRows; 
    }

}
