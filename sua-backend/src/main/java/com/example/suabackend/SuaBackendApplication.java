package com.example.suabackend;

import com.example.suabackend.utils.Utils;
import jdk.jshell.execution.Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.MessagingException;
import java.io.IOException;

import static com.example.suabackend.utils.Utils.BASE_URL;

@SpringBootApplication
public class SuaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuaBackendApplication.class, args);
		System.out.println("http://127.0.0.1:8080" + BASE_URL);


    }

}
