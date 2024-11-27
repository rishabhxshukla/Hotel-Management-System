package com.hotel.Receptionist.controller;

import com.hotel.Receptionist.model.Guest;
import com.hotel.Receptionist.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GuestController
{
    @Autowired
    private GuestService service;

    @GetMapping("/guestDetails/show")
    public void showAllGuests()
    {
        //
    }

    @GetMapping("/guestDetails/show/{id}")
    public void showGuestById(@PathVariable int id)
    {
        //
    }

    @PostMapping("/guestDetails/add")
    public void addGuest(@RequestBody Guest guest)
    {
        //
    }

    @PutMapping("/guestDetails/update")
    public void updateGuest(@RequestBody Guest guest)
    {
        //
    }

    @DeleteMapping("/guestDetails/delete/{id}")
    public void deleteGuest(@PathVariable int id)
    {
        //
    }


}