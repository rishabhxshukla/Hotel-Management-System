package com.hotel.Receptionist.dto;

import lombok.Data;

@Data
public class Room
{
    private int roomId;
    private String type;
    private int price;
    private String available;
}