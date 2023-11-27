package ar.com.tdm.weather.dao;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ar.com.tdm.weather.entities.cities.City;
import ar.com.tdm.weather.exceptions.CustomException;

@Repository
public class DaoCitiesImpl implements IDaoCities {
	private final Logger log = LoggerFactory.getLogger(DaoCitiesImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate; 
	
	/**
	 * Este metodo se encaga de conectarse a la DB y realizar una onsersion masiva.
	 * @Param cities (ArrayList)
	 * @Return int, el cual representa los rows insertados.
	 */
    @Override	
	public int bulkInsertCities(ArrayList<City> cities) {
        String sql = "INSERT INTO cities (name, code) VALUES (?, ?)";
        int[][] affectedRows =  null;
        try {
        	affectedRows = jdbcTemplate.batchUpdate(sql, cities, cities.size(), (ps, city) -> {
                ps.setString(1, city.getName().toLowerCase().trim());
                ps.setString(2, city.getCode().trim());
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

	
    /**
     * este metodo sirve para obtener el codigo de una ciudad determinada si es que existe en la db
     * @param String city
     * @return String (code)
     * @throws CustomException 
     */
    @Override
	public String getCode(String city)  {
    	String query = "SELECT top 1 code FROM CITIES WHERE name = ?";
        log.info("DaoCitiesImpl: getCode: query: " + query);
        
        try {
            
            String  code = jdbcTemplate.queryForObject(query, new Object[] {city}, String.class);
            log.info("DaoCitiesImpl: getCode: el codigo para la ciudad "+city+" es: " + code);

            return code;
        }
        catch (EmptyResultDataAccessException e) {
            log.error("DaoCitiesImpl: getCode: No existe "+city+" en la base de datos. error: "+e);
        } 
        catch(Exception e) {
            log.error("DaoCitiesImpl: getCode: error: Algo salio mal, no se obtuvieron los registros. Motivo: " + e);
        }
        return "";
	}


	
    /**
     * Este metodo existe solo a fines de poder realizar pruebas manuales para vaciar la tabla y volver a completarla con loadCities
     * @return int deleteRows
     */
    @Override
	public int cleanTable() {
    	String query = "Delete FROM CITIES";
        log.info("DaoCitiesImpl: cleanTable: query: " + query);
        
        try {
            
            int deleteRows = jdbcTemplate.update(query);
            log.info("DaoCitiesImpl: cleanTable: Se eliminaron "+deleteRows+" filas.");

            return deleteRows;
        }
        catch(Exception e) { 
            log.error("DaoCitiesImpl: getCode: error: Algo salio mal, no se obtuvieron los registros. Motivo: " + e);
            return -1;
        }
	}
    
}
