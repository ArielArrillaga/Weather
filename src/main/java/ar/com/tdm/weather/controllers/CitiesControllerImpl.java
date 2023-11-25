package ar.com.tdm.weather.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/weather")
@CrossOrigin(allowCredentials = "true", origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET,
		RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS, RequestMethod.PATCH,
		RequestMethod.HEAD })
public class CitiesControllerImpl implements CitiesController {
	//@Autowired
	//private CitiesService service;


	@Override
	@GetMapping("/getClima")
	public Boolean loadCities() {
		// TODO Auto-generated method stub
		return null;
	}


	
}