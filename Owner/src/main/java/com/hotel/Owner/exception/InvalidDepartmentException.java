package com.hotel.Owner.exception;

public class InvalidDepartmentException extends RuntimeException
{
	public InvalidDepartmentException(String message)
	{
		super(message);
	}
}