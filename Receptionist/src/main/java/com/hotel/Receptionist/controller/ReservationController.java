package com.hotel.Receptionist.controller;

import com.hotel.Receptionist.model.Reservation;
import com.hotel.Receptionist.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receptionist/reservation")
public class ReservationController
{
    @Autowired
    ReservationService service;

    @GetMapping("/show")
    public List<Reservation> showAllReservations()
    {
        return service.showAllReservations();
    }

    @GetMapping("/show/{id}")
    public Reservation showReservationById(@PathVariable int id)
    {
        return service.showReservationById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addReservation(@Valid @RequestBody Reservation Reservation)
    {
        return service.addReservation(Reservation);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable int id)
    {
        service.deleteReservation(id);

        return new ResponseEntity<>("Reservation deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/show/{id}/bill")
    public ResponseEntity<String> calculateBill(@PathVariable int id)
    {
        String bill = "<h1>"
                    + "Bill for reservation id "
                    + id
                    + " : "
                    + service.calculateBill(id)
                    + "</h1>";

        return new ResponseEntity<>(bill, HttpStatus.OK);
    }
}