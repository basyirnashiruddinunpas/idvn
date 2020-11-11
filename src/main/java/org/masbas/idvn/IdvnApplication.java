package org.masbas.idvn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class IdvnApplication extends SpringBootServletInitializer  {

	public static void main(String[] args) {
		SpringApplication.run(IdvnApplication.class, args);
	}

}
