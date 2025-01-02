package com.hotel.Receptionist.controller;

import com.hotel.Receptionist.model.Guest;
import com.hotel.Receptionist.service.GuestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receptionist/guestDetails")
public class GuestController
{
    @Autowired
    private GuestService service;

    @GetMapping("/show")
    public List<Guest> showAllGuests()
    {
        return service.showAllGuests();
    }

    @GetMapping("/show/{id}")
    public Guest showGuestById(@PathVariable int id)
    {
        return service.showGuestById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addGuest(@Valid @RequestBody Guest guest)
    {
        service.addGuest(guest);

        return new ResponseEntity<>("Guest inserted successfully!", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateGuest(@PathVariable int id, @Valid @RequestBody Guest guest)
    {
        service.updateGuest(id, guest);

        return new ResponseEntity<>("Guest updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGuest(@PathVariable int id)
    {
        service.deleteGuest(id);

        return new ResponseEntity<>("Guest deleted successfully!", HttpStatus.OK);
    }
}