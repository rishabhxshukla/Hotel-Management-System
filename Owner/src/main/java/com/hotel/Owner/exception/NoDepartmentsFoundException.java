package com.hotel.Owner.exception;

public class NoDepartmentsFoundException extends RuntimeException
{
	public NoDepartmentsFoundException (String message)
	{
		super(message);
	}
}