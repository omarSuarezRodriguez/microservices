package com.formacionbdi.microservicios.app.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication
public class MicroserviciosUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosUsuariosApplication.class, args);
		//System.out.println("Hola Mundosss");
	}

}
