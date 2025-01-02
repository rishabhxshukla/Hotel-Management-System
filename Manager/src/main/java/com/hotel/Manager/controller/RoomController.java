package com.hotel.Manager.controller;

import com.hotel.Manager.model.Room;
import com.hotel.Manager.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager/roomDetails")
public class RoomController
{
    @Autowired
    RoomService service;

    @GetMapping("/show")
    public List<Room> showAllRooms()
    {
        return service.showAllRooms();
    }

    @GetMapping("/show/{id}")
    public Room showRoomById(@PathVariable int id)
    {
        return service.showRoomById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addRoom(@Valid @RequestBody Room Room)
    {
        service.addRoom(Room);

        return new ResponseEntity<>("Room inserted successfully!", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRoom(@PathVariable int id, @Valid @RequestBody Room Room)
    {
        service.updateRoom(id, Room);

        return new ResponseEntity<>("Room updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable int id)
    {
        service.deleteRoom(id);

        return new ResponseEntity<>("Room deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/checkAvailability")
    public ResponseEntity<String> checkRoomAvailability(@RequestParam int id)
    {
        boolean available = service.checkRoomAvailability(id);

        return new ResponseEntity<>(String.valueOf(available), HttpStatus.OK);
    }

    @PutMapping("/update/availability/{id}")
    public ResponseEntity<String> updateRoomAvailability(@PathVariable int id, @RequestParam String available)
    {
        service.updateRoomAvailability(id, available);

        return new ResponseEntity<>("Room availability updated successfully!", HttpStatus.OK);
    }
}