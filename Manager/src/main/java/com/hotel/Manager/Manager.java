package com.hotel.Manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Manager
{
	public static void main(String[] args)
	{
		SpringApplication.run(Manager.class, args);
		System.out.println("Manager microservice started on port 2000...");
	}
}