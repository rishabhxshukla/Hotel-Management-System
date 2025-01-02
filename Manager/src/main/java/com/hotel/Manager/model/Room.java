package com.hotel.Manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Room
{
    @Id
    @Positive(message = "Room ID must be a positive number")
    private int roomId;

    @NotBlank(message = "Room type cannot be blank")
    @Size(message = "Room type should be less than or equal to 15 characters", max = 15)
    private String type;

    @Positive(message = "Price must be a positive number")
    private int price;

    @NotBlank(message = "Availability status cannot be blank")
    @Pattern(regexp = "^(Yes|No)$", message = "Availability status must be 'Yes' or 'No'")
    private String available;
}