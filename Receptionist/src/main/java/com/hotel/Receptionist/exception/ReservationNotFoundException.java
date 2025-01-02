package com.hotel.Receptionist.exception;

public class ReservationNotFoundException extends RuntimeException
{
    public ReservationNotFoundException(String message)
    {
        super(message);
    }
}