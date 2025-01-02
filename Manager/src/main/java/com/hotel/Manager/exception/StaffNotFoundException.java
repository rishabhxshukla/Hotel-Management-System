package com.hotel.Manager.exception;

public class StaffNotFoundException extends RuntimeException
{
    public StaffNotFoundException(String message)
    {
        super(message);
    }
}