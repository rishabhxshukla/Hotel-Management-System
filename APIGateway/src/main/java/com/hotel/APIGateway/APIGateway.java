package com.hotel.APIGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class APIGateway
{
	public static void main(String[] args)
	{
		SpringApplication.run(APIGateway.class, args);
		System.out.println("API Gateway started on port 8080...");
	}
}