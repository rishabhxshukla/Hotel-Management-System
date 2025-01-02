package com.hotel.Receptionist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Reservation
{
    @Id
    private int roomId;

    @Min(message = "Members cannot be negative", value = 1)
    private int members;

    @NotBlank(message = "Check-in date cannot be blank")
    private String checkInDate;

    @NotBlank(message = "Check-out date cannot be blank")
    private String checkOutDate;

    @Min(message = "No. of nights cannot be negative", value = 1)
    private int noOfNights;
}