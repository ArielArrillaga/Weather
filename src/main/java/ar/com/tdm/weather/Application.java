package ar.com.tdm.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("file:/app/weather/config.properties")
public class Application  extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		java.security.Security.setProperty("jdk.tls.disabledAlgorithms", 
			    "SSLv3, RC4, MD5withRSA, DH keySize < 768, EC keySize < 224");
		SpringApplication.run(Application.class, args);
	}

}
