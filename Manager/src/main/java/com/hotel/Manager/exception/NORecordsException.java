package com.hotel.Manager.exception;

public class NORecordsException extends RuntimeException
{
    public NORecordsException(String message)
    {
        super(message);
    }
}