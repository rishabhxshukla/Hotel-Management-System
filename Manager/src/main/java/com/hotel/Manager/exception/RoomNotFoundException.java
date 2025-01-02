package com.hotel.Manager.exception;

public class RoomNotFoundException extends RuntimeException
{
    public RoomNotFoundException(String message)
    {
        super(message);
    }
}