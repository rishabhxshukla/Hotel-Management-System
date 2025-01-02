package com.hotel.Receptionist.exception;

public class GuestNotFoundException extends RuntimeException
{
    public GuestNotFoundException(String message)
    {
        super(message);
    }
}