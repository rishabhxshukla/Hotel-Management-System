package com.hotel.Manager.exception;

public class NoStaffFoundException extends RuntimeException
{
	public NoStaffFoundException(String message)
	{
		super(message);
	}
}