package com.hotel.Owner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Owner
{
	public static void main(String[] args)
	{
		SpringApplication.run(Owner.class, args);
		System.out.println("Owner microservice started on port 1000...");
	}
}