package com.eva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.eva.config.property.EvaApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(EvaApiProperty.class)
public class EvartsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvartsApplication.class, args);
	}

}
