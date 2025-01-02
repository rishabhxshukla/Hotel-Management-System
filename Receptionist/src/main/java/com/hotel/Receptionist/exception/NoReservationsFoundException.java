package com.hotel.Receptionist.exception;

public class NoReservationsFoundException extends RuntimeException
{
    public NoReservationsFoundException(String message)
    {
        super(message);
    }
}