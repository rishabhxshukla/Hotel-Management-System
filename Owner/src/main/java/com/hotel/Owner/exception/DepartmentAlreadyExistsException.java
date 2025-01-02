package com.hotel.Owner.exception;

public class DepartmentAlreadyExistsException extends RuntimeException
{
	public DepartmentAlreadyExistsException(String message)
	{
		super(message);
	}
}