package com.hotel.Receptionist.controller;

import com.hotel.Receptionist.model.Guest;
import com.hotel.Receptionist.service.GuestService;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GuestController
{
    @Autowired
    private GuestService service;

    @GetMapping("/guestDetails/show")
    public List<Guest> showAllGuests()
    {
        return service.showAllGuest();
    }

    @GetMapping("/guestDetails/show/{id}")
    public Optional<Guest> showGuestById(@PathVariable int id)
    {
        return service.showGuestById(id);
    }

    @PostMapping("/guestDetails/add")
    public void addGuest(@RequestBody Guest guest)
    {
        service.addGuest(guest);
    }

    @PutMapping("/guestDetails/update")
    public void updateGuest(@RequestBody Guest guest)
    {
        service.updateGuest(guest);
    }

    @DeleteMapping("/guestDetails/delete/{id}")
    public void deleteGuest(@PathVariable int id)
    {
        service.deleteGuest(id);
    }
}