package com.inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = { "com.inventario.modules.*.*.controller",
		"com.inventario.modules.*.controller", "com.inventario.modules.*.*.service", "com.inventario.modules.*.service",
		"com.inventario.configurations" })
public class InventarioVacunasApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioVacunasApplication.class, args);
	}

}
