package com.hotel.Owner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Report
{
    private int totalRooms;
    private int availableRooms;
    private int totalEmployees;
    private double totalSalary;
}