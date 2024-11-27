package com.hotel.Receptionist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Guest
{
    @Id
    private int guestId;
    private String name;
    private String gender;
    private String email;
    private String phoneNo;
    private String company;
    private String address;
}