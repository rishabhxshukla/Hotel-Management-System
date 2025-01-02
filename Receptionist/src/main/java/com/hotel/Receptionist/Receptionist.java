package com.hotel.Receptionist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Receptionist
{
	public static void main(String[] args)
	{
		SpringApplication.run(Receptionist.class, args);
		System.out.println("Receptionist microservice started on port 3000...");
	}
}