package com.hotel.Manager.service;

import com.hotel.Manager.exception.NoRoomsFoundException;
import com.hotel.Manager.exception.RoomNotFoundException;
import com.hotel.Manager.model.Room;
import com.hotel.Manager.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService
{
    @Autowired
    RoomRepository repo;

    public List<Room> showAllRooms()
    {
        //Fetch all rooms
        List<Room> rooms = repo.findAll();

        //Check if list is empty
        if (rooms.isEmpty()) {
            throw new NoRoomsFoundException("Table is empty. No rooms found in the database!");
        }

        return rooms;
    }

    public Room showRoomById(int id)
    {
        //Check if room is not present
        if (!repo.findById(id).isPresent()) {
            throw new RoomNotFoundException("Room with ID " + id + " not found!");
        }

        return repo.findById(id).get();
    }

    public void addRoom(Room room)
    {
        repo.save(room);
    }

    public void updateRoom(int id, Room requestBody)
    {
        //Check if room is not present
        if (!repo.findById(id).isPresent()) {
            throw new RoomNotFoundException("Room with ID " + id + " not found!");
        }

        //Fetch the room
        Room room = repo.findById(id).get();

        //Change values coming from request body
        room.setType(requestBody.getType());
        room.setPrice(requestBody.getPrice());
        room.setAvailable(requestBody.getAvailable());

        //Save the room
        repo.save(room);
    }

    public void deleteRoom(int id)
    {
        //Check if room is not present
        if (!repo.findById(id).isPresent()) {
            throw new RoomNotFoundException("Room with ID " + id + " not found!");
        }

        repo.deleteById(id);
    }

    public void setRoomAvailability(int id, String availability)
    {
        Room room = repo.findById(id).get();

        room.setAvailable(availability);

        repo.save(room);
    }

    public boolean checkRoomAvailability(int id)
    {
        //Check if room is not present
        if (!repo.findById(id).isPresent()) {
            throw new RoomNotFoundException("Room with ID " + id + " not found!");
        }

        Room room = repo.findById(id).get();

        return "Yes".equalsIgnoreCase(room.getAvailable());
    }

    public void updateRoomAvailability(int id, String available)
    {
        //Check if room is not present
        if (!repo.findById(id).isPresent()) {
            throw new RoomNotFoundException("Room with ID " + id + " not found!");
        }

        Room room = repo.findById(id).get();

        room.setAvailable(available);

        repo.save(room);
    }
}