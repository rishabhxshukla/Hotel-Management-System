package com.hotel.Receptionist.exception;

public class NoGuestsFoundException extends RuntimeException
{
    public NoGuestsFoundException(String message)
    {
        super(message);
    }
}