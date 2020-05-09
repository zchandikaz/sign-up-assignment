package com.example.suabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.suabackend.utils.Utils.BASE_URL;

@SpringBootApplication
public class SuaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuaBackendApplication.class, args);
		System.out.println("http://localhost:8080" + BASE_URL);
	}

}
