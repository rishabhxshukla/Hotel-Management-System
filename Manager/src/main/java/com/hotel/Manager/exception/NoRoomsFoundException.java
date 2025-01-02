package com.hotel.Manager.exception;

public class NoRoomsFoundException extends RuntimeException
{
    public NoRoomsFoundException(String message)
    {
        super(message);
    }
}