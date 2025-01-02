package com.hotel.Receptionist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Guest
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int guestId;

    @NotBlank(message = "Guest name cannot be blank")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String name;

    @NotBlank(message = "Gender cannot be blank")
    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
    private String gender;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be of 10 digits")
    private String phoneNo;

    @Size(max = 50, message = "Company name should be less than or equal to 50 characters")
    private String company;

    @NotBlank(message = "Address cannot be blank")
    @Size(max = 100, message = "Address should be less than or equal to 100 characters")
    private String address;
}